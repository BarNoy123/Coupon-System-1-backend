package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import beans.CATEGORY;
import db.ConvertUtils;
import db.JDBCUtils;

public class CategoryDAOImpl implements CategoryDAO{

    private static final String INSERT_CATEGORY = "INSERT INTO `bar_coupon_system`.`categories` (`name`) VALUES (?)";

    private static final String GET_ALL_CATEGORY = "SELECT * FROM `bar_coupon_system`.`categories`";

    @Override
    public void addCategory(String name) throws SQLException {

        Map<Integer, Object> params = new HashMap<>();
        params.put(1, name);

        JDBCUtils.runQuery(INSERT_CATEGORY,params);


    }

    @Override
    public List<CATEGORY> getAllCategory() throws SQLException {
        List<CATEGORY> categories = new ArrayList<>();
        List<?> list = JDBCUtils.runQueryWithResult(GET_ALL_CATEGORY);
        for (Object obj:list) {
            CATEGORY category = ConvertUtils.objectToCategory((Map<String, Object>) obj);
            categories.add(category);
        }
        return categories;

    }



}
