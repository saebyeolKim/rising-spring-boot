package com.example.demo.src.region;

import com.example.demo.src.region.model.GetRegionRes;
import com.example.demo.src.region.model.PatchRegionReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class RegionDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {this.jdbcTemplate = new JdbcTemplate(dataSource);}

    public List<GetRegionRes> getRegions() {
        String getRegionsQuery = "SELECT REGION_NAME, REGION_DETAIL FROM REGION" +
                "WHERE NATION_REGION IS NULL AND REGION_NAME = '서울' AND DEL_YN = 'N'";
        return this.jdbcTemplate.query(getRegionsQuery,
                (rs, rowNum) -> new GetRegionRes(
                        rs.getString("nationRegion"),
                        rs.getString(("regionName")),
                        rs.getString("regionDetail"))
                );
    }

    public int patchRegionReqs(PatchRegionReq patchRegionReq) {
        String modifyRegionQuery = "UPDATE REGION SET REGION_DETAIL = ? WHERE REGION_ID = ? ";
        Object[] modifyRegionNameParams = new Object[]{patchRegionReq.getRegionName(), patchRegionReq.getRegionId()};

        return this.jdbcTemplate.update(modifyRegionQuery,modifyRegionNameParams);
    }
}
