package com.govmedcare.dao;
import com.govmedcare.model.Category;
import com.govmedcare.repository.CategoryRepository;
import com.govmedcare.utils.DBConnection;
import com.govmedcare.utils.QueryUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategoryDao implements CategoryRepository {
    public static Logger logger=Logger.getLogger(CategoryDao.class.getName());
    @Override
    public List<Category> getAllCategory() {
        List<Category> list=new ArrayList<>();
        try(Connection conn= DBConnection.getConnection();
            PreparedStatement ps=conn.prepareStatement(QueryUtil.getCategories)
        ){
            ResultSet rs= ps.executeQuery();
            while (rs.next()){
                Category category=new Category();
                category.setCategory_id(rs.getLong("category_id"));
                category.setCategory_name(rs.getString("name"));
                category.setDescription(rs.getString("description"));
                list.add(category);
            }

        }
        catch (SQLException e){
            logger.log(Level.SEVERE,"Failed to get categories",e.getMessage());
        }
        return list;
    }
}
