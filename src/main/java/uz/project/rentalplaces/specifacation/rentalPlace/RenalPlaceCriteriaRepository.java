package uz.project.rentalplaces.specifacation.rentalPlace;


import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uz.project.rentalplaces.dto.place.ActivePlaceListDto;
import uz.project.rentalplaces.entity.Attachment;
import uz.project.rentalplaces.service.AttachmentService;
import uz.project.rentalplaces.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class RenalPlaceCriteriaRepository {
    private final AttachmentService attachmentService;
    private final JdbcTemplate jdbcTemplate;

    public PageImpl<ActivePlaceListDto> findAllWithFilters(RenalPlacePage page, RentalPlaceSearchCriteria searchCriteria) {
        Sort sort = Sort.by(page.getSortDirection(), page.getSortBy());
        Pageable pageable = PageRequest.of(page.getPageNumber(), page.getPageSize(), sort);
        StringBuilder query = new StringBuilder(querySql);
        StringBuilder countQuery = new StringBuilder(querySqlCount);
        List<String> conditions = new ArrayList<>();
        ArrayList params = new ArrayList<>();
        ArrayList params2 = new ArrayList<>();
        if (Objects.nonNull(searchCriteria.getRentalPlaceType())) {
            params.add(searchCriteria.getRentalPlaceType().getText());
            params2.add(searchCriteria.getRentalPlaceType().getText());
            conditions.add(" place_type =? ");
        }
        if (Objects.nonNull(searchCriteria.getRentFor())) {
            params.add(searchCriteria.getRentFor().getText());
            params2.add(searchCriteria.getRentFor().getText());
            conditions.add(" rent_for = ? ");
        }
        if (Objects.nonNull(searchCriteria.getDay())) {
            params.add(searchCriteria.getDay());
            params2.add(searchCriteria.getDay());
            conditions.add(" active_day = ? ");
        }
        if (Objects.nonNull(searchCriteria.getOwnerId())) {
            params.add(searchCriteria.getOwnerId());
            params2.add(searchCriteria.getOwnerId());
            conditions.add(" owner_id = ? ");
        }
        if (Objects.nonNull(searchCriteria.getActive())) {
            params.add(searchCriteria.getActive());
            params2.add(searchCriteria.getActive());
            conditions.add(" active = ? ");
        }

        if (!conditions.isEmpty()) {
            query.append(" WHERE ").append(String.join(" AND ", conditions));
            countQuery.append(" WHERE ").append(String.join(" AND ", conditions));
        }

        query.append(" ORDER BY ").append(page.getSortBy()).append(" ").append(page.getSortDirection());
        query.append(" LIMIT ? OFFSET ?");
        params.add(page.getPageSize());
        params.add(page.getPageSize() * page.getPageNumber());
        try {
            List<Map<String, Object>> maps = jdbcTemplate.queryForList(query.toString(), params.toArray());
            Long total = jdbcTemplate.queryForObject(countQuery.toString(), params2.toArray() , Long.class);
            TypeReference<ArrayList<ActivePlaceListDto>> typeReference = new TypeReference<ArrayList<ActivePlaceListDto>>() {
            };
            ArrayList<ActivePlaceListDto> creditRequestLogPsList = AppUtils.convertWithJackson(maps, typeReference);
            creditRequestLogPsList.forEach(obj->{
                List<Map<String, Object>> maps1 = jdbcTemplate.queryForList(qr, obj.getRentalPlaceId());
                TypeReference<ArrayList<Attachment>> typeReference1 = new TypeReference<ArrayList<Attachment>>() {
                };
                ArrayList<Attachment> attachmentArrayList = AppUtils.convertWithJackson(maps1, typeReference1);
                obj.setPhotos(attachmentService.getUrlList(attachmentArrayList));
            });
            return new PageImpl<>(creditRequestLogPsList, pageable, total);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new PageImpl<>(new ArrayList<>(), pageable, 0);
    }


    String qr = "select a.* from attachment a , rental_place_photos rp where rp.rental_place_entity_id = ? and rp.photos_id = a.id";
    private String querySql = "with data as (select ap.id as  active_id,rp.id as rental_place_id , ap.price , ap.day as active_day ,ap.active\n" +
            "                      , rp.place_type ,rp.rent_for , rp.info ,rp.owner_id ,rp.latitude ,\n" +
            "                     rp.longitude, u.name as owner_name ,u.phone as owner_phone\n" +
            "              from active_place ap , rental_place rp ,users u  where ap.place_id = rp.id and rp.owner_id = u.id)\n" +
            "select * from data";
    private String querySqlCount = "with data as (select ap.id as  active_id,rp.id as rental_place_id , ap.price , ap.day as active_day ,ap.active\n" +
            "                      , rp.place_type ,rp.rent_for , rp.info ,rp.owner_id ,rp.latitude ,\n" +
            "                     rp.longitude, u.name as owner_name ,u.phone as owner_phone\n" +
            "              from active_place ap , rental_place rp ,users u  where ap.place_id = rp.id and rp.owner_id = u.id)\n" +
            "select count(*) from data";
}
