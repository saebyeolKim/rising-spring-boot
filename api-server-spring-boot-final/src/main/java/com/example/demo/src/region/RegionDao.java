package com.example.demo.src.region;

import com.example.demo.src.region.model.GetRegionRes;
import com.example.demo.src.region.model.PatchRegionReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class RegionDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {this.jdbcTemplate = new JdbcTemplate(dataSource);}

    public List<GetRegionRes> getRegions() {
        String getRegionsQuery = "SELECT NATION_REGION, REGION_NAME, REGION_DETAIL FROM REGION";
        return this.jdbcTemplate.query(getRegionsQuery,
                (rs, rowNum) -> new GetRegionRes(
                        rs.getString("NATION_REGION"),
                        rs.getString(("REGION_NAME")),
                        rs.getString("REGION_DETAIL"))
                );
    }

    public int patchRegionReqs(PatchRegionReq patchRegionReq) {
        String modifyRegionQuery = "UPDATE REGION SET REGION_DETAIL = ? WHERE REGION_ID = ? ";
        Object[] modifyRegionNameParams = new Object[]{patchRegionReq.getRegionName(), patchRegionReq.getRegionId()};

        return this.jdbcTemplate.update(modifyRegionQuery,modifyRegionNameParams);
    }
}
