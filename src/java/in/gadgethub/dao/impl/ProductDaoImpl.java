/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.gadgethub.dao.impl;

import in.gadgethub.dao.ProductDao;
import in.gadgethub.pojo.ProductPojo;
import in.gadgethub.utility.DBUtil;
import in.gadgethub.utility.IDutil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ProductDaoImpl implements ProductDao{
    
    public String addProduct(ProductPojo product){
        String status="Product Registration Failed!";
        if(product.getProdId()==null){
            product.setProdId(IDutil.generateProdId());
        }
        Connection conn=DBUtil.provideConnection();
        PreparedStatement ps=null;
        try{
            ps=conn.prepareStatement("Insert into products values(?,?,?,?,?,?,?)");
            ps.setString(1,product.getProdId());
            ps.setString(2,product.getProdName());
            ps.setString(3,product.getProdType());
            ps.setString(4,product.getProdInfo());
            ps.setDouble(5,product.getProdPrice());
            ps.setInt(6,product.getProdQuantity());
            ps.setBlob(7,product.getProdImage());
            int count=ps.executeUpdate();
            if(count==1){
                status="Prodcut Added successfully With Id:"+product.getProdId();               
            }
        }catch(SQLException ex){
            System.out.println("Error in addProduct:"+ex);
            ex.printStackTrace();
        } 
        DBUtil.closeStatement(ps);
        return status;
    }

    public String updateProduct(ProductPojo prevProduct,ProductPojo updatedProduct){
        String status="Updation Failed!";
        if(!prevProduct.getProdId().equals(updatedProduct.getProdId())){
            status="Product ID's Do Not Match. Updation Failed";
            return status;
        }
        Connection conn=DBUtil.provideConnection();
        PreparedStatement ps=null;
        try{
            ps=conn.prepareStatement("Update products set pname=?,ptype=?,pinfo=?,pprice=?,pquantity=?,image=? where pid=?");
            ps.setString(1,updatedProduct.getProdName());
            ps.setString(2,updatedProduct.getProdType());
            ps.setString(3,updatedProduct.getProdInfo());
            ps.setDouble(4,updatedProduct.getProdPrice());
            ps.setInt(5,updatedProduct.getProdQuantity());
            ps.setBlob(6,updatedProduct.getProdImage());
            ps.setString(7,updatedProduct.getProdId());
            int count=ps.executeUpdate();
            if(count==1){
                status="Product Updated Successfully";
               
            }
        }catch(SQLException ex){
            System.out.println("Error in updateProduct:"+ex);
            ex.printStackTrace();
        } 
        DBUtil.closeStatement(ps);
        return status;
    }
    
    public String updateProductPrice(String prodId,double prodPrice){
        String status="Price Updation Failed!";
        Connection conn=DBUtil.provideConnection();
        PreparedStatement ps=null;
        try{
            ps=conn.prepareStatement("Update products set pprice=?where pid=?");
            ps.setDouble(1,prodPrice);
            ps.setString(2,prodId);
            int count=ps.executeUpdate();
            if(count==1){
                status="Product Updated Successfully";
               
            }
        }catch(SQLException ex){
            status="Error:"+ex.getMessage();
            System.out.println("Error in updateProductPrice:"+ex);
            ex.printStackTrace();
        } 
        DBUtil.closeStatement(ps);
        return status;
    }
public List<ProductPojo> getAllProducts(){
        List <ProductPojo> productList=new ArrayList<>();
        Connection conn=DBUtil.provideConnection();
        Statement st=null;
        ResultSet rs=null;
        try{
            st=conn.createStatement();
            rs=st.executeQuery("Select * from Products");
            while(rs.next()){
                ProductPojo product=new ProductPojo();
                product.setProdId(rs.getString("pid"));
                product.setProdName(rs.getString("pname"));
                product.setProdPrice(rs.getDouble("pprice"));
                product.setProdType(rs.getString("ptype"));
                product.setProdInfo(rs.getString("pinfo"));
                product.setProdQuantity(rs.getInt("pquantity"));
                product.setProdImage(rs.getAsciiStream("image"));
                productList.add(product);
                
            }
        }
        catch(SQLException ex){
            System.out.println("Error in getAllProducts:"+ex);
            ex.printStackTrace();
        } 
        DBUtil.closeResultSet(rs);
        DBUtil.closeStatement(st);
        return productList;
    } 
public List<ProductPojo> getAllProductsByType(String ptype){
        List <ProductPojo> productList=new ArrayList<>();
        Connection conn=DBUtil.provideConnection();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{
            ps=conn.prepareStatement("Select * from products where ptype like ?");
            ps.setString(1,"%"+ptype+"%");
            rs=ps.executeQuery();
            while(rs.next()){
                ProductPojo product=new ProductPojo();
                product.setProdId(rs.getString("pid"));
                product.setProdName(rs.getString("pname"));
                product.setProdPrice(rs.getDouble("pprice"));
                product.setProdType(rs.getString("ptype"));
                product.setProdInfo(rs.getString("pinfo"));
                product.setProdQuantity(rs.getInt("pquantity"));
                product.setProdImage(rs.getAsciiStream("image"));
                productList.add(product);
                
            }
        }
        catch(SQLException ex){
            System.out.println("Error in getAllProductsByType:"+ex);
            ex.printStackTrace();
        } 
        DBUtil.closeResultSet(rs);
        DBUtil.closeStatement(ps);
        return productList;
    } 
    
}
