package uz.project.rentalplaces.service;

import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.project.rentalplaces.dto.sms.SmsModel;
import uz.project.rentalplaces.dto.sms.SmsResponse;
import uz.project.rentalplaces.dto.sms.SmsTokenDto;
import uz.project.rentalplaces.dto.user.UserVerifyRequestDto;
import uz.project.rentalplaces.entity.SmsEntity;
import uz.project.rentalplaces.entity.SmsServiceTokenEntity;
import uz.project.rentalplaces.exception.SmsException;
import uz.project.rentalplaces.properties.SmsServiceProperties;
import uz.project.rentalplaces.repository.SmsRepository;
import uz.project.rentalplaces.repository.SmsServiceTokenRepository;
import uz.project.rentalplaces.utils.AppUtils;


import java.time.LocalDateTime;
import java.util.List;

import static uz.project.rentalplaces.constants.Constants.*;


@Service
@RequiredArgsConstructor
public class SmsService {

    private final SmsServiceProperties properties;

    private final SmsServiceTokenRepository smsServiceTokenRepository;

    private final SmsRepository smsRepository;


    public String getToken() {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("email", properties.getUsername())
                .addFormDataPart("password", properties.getPassword())
                .build();
        Request request = new Request.Builder()
                .url(properties.getUrl() + "/api/auth/login")
                .post(requestBody)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();
            if (response.code() != HttpStatus.OK.value()) {
                throw new SmsException(CAN_NOT_TAKE_SMS_SENDING_SERVICE_TOKEN);
            }
            SmsTokenDto smsTokenDto = AppUtils.jsonTreeToObjectByGson(responseBody, SmsTokenDto.class);
            SmsServiceTokenEntity entity = smsTokenDto.getData();
            List<SmsServiceTokenEntity> all = smsServiceTokenRepository.findAll();
            if (!all.isEmpty()){
                SmsServiceTokenEntity entity1 = all.get(0);
                entity1.setToken(entity.getToken());
                entity = smsServiceTokenRepository.save(entity1);
            }else {
                entity = smsServiceTokenRepository.save(entity);
            }
            return entity.getToken();
        } catch (Exception e) {
            throw new SmsException(e.getMessage());
        }
    }

    public SmsResponse send(String phone, String message, String code) {
        SmsEntity smsEntity = SmsEntity.toEntity(phone, message, code);
//        SmsResponse smsResponse = sendSms(SmsModel.builder()
//                .phone(phone)
//                .message(message)
//                .from(4546)
//                .callback_url("http://0000.uz/test.php")
//                .build());

        smsRepository.save(smsEntity);
//        return smsResponse;
        return null;
    }

    public SmsResponse sendSms(SmsModel smsModel) {
        OkHttpClient client = new OkHttpClient();
        List<SmsServiceTokenEntity> all = smsServiceTokenRepository.findAll();
        String token = all.get(0).getToken();
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("mobile_phone", smsModel.getPhone())
                .addFormDataPart("message", smsModel.getMessage())
                .addFormDataPart("from", "4546")
                .addFormDataPart("callback_url", "http://0000.uz/test.php")
                .build();

        Request request = new Request.Builder()
                .url(properties.getUrl() + "/api/message/sms/send")
                .addHeader("Authorization", "Bearer " + token)
                .post(requestBody)
                .build();

        try {
            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();
            if (response.code() == HttpStatus.UNAUTHORIZED.value()) {
                getToken();
                sendSms(smsModel);
            } else if (response.code() == HttpStatus.OK.value()) {
                throw new SmsException(CAN_NOT_SEND_SMS);
            }
          return   AppUtils.jsonTreeToObjectByJackson(responseBody, SmsResponse.class);
        } catch (Exception e) {
            throw new SmsException(CAN_NOT_SEND_SMS);
        }
    }

    private String reFreshToken(String oldToken) {
        String url = properties.getUrl() + "/api/auth/refresh";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer "+ oldToken)
                .patch(RequestBody.create(null, new byte[0])) // Empty body for PATCH request
                .build();

        try {
            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();
            if (response.code() != HttpStatus.OK.value()) {
                throw new SmsException(CAN_NOT_TAKE_SMS_SENDING_SERVICE_TOKEN);
            }
            SmsTokenDto smsTokenDto = AppUtils.jsonTreeToObjectByGson(responseBody, SmsTokenDto.class);
            SmsServiceTokenEntity entity = smsTokenDto.getData();
            List<SmsServiceTokenEntity> all = smsServiceTokenRepository.findAll();
            if (!all.isEmpty()){
                SmsServiceTokenEntity entity1 = all.get(0);
                entity1.setToken(entity.getToken());
                entity = smsServiceTokenRepository.save(entity1);
            }else {
                entity = smsServiceTokenRepository.save(entity);
            }
            return entity.getToken();
        } catch (Exception e) {
            throw new SmsException(CAN_NOT_TAKE_SMS_SENDING_SERVICE_TOKEN);
        }
    }

    public boolean findByPhoneAndCheck(UserVerifyRequestDto dto) {
        SmsEntity smsEntity = smsRepository.findByPhoneAndCode(dto.getPhone(), dto.getVerificationCode()).orElseThrow(() -> new SmsException(SMS_NOT_SEND_THIS_NUMBER));
        if (LocalDateTime.now().isAfter(smsEntity.getExpireAt())) {
            throw new SmsException(SMS_CODE_TIME_EXPIRE);
        }
        return true;
    }
}
