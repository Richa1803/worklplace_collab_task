package com.v1.api.Endpoints.HomePage.Services;

import com.v1.api.Endpoints.HomePage.Models.MainRequestModel;
import org.springframework.stereotype.Service;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Service
public class HomePageService {

    public List<String> GetTableInfo(MainRequestModel requestModel) throws SQLException {
        String db_url = "jdbc:mysql://127.0.0.1:3306/homepage_banner?useSSL=false&serverTimezone=UTC";
        String db_username = "root";
        String db_password = "Root@1234";
        Connection con = null;
        Statement st = null;
        ResultSet rs;
        String query = null;
        List<String> columnNames = new ArrayList<>();
        try {
            con = DriverManager.getConnection(db_url, db_username, db_password);
            st = con.createStatement();

            switch (requestModel.getWidget_name()) {
                case "tiles":
                    query = "SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'widget_tiles';";
                    break;

                case "lenses":
                case "polaroid":
                    query = "SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'newigp_widget_polaroid_lenses';";
                    break;

                case "country-city":
                    query = "SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'newigp_country_city_strips_widget_tmp';";
                    break;

                case "banner":
                case "split-banner":
                case "strip-banner":
                    query = "SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'homepage_banner_widget_tmp';";
                    break;

                case "title":
                    query = "SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'newigp_widget_title';";
                    break;

                case "reels":
                    query = "SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'widget_reels';";
                    break;

            }
            rs = st.executeQuery(query);
            while (rs.next()) {
                String column = rs.getString("COLUMN_NAME");
                columnNames.add(column);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            st.close();
            con.close();

        }
        return columnNames;
    }


    public boolean InsertIntoHomepageBanner(MainRequestModel requestModel) throws SQLException {

        String db_url = "jdbc:mysql://127.0.0.1:3306/homepage_banner?useSSL=false&serverTimezone=UTC";
        String db_username = "root";
        String db_password = "root";
        boolean response = false;
        Connection con = null;
        Statement st = null;
        try {
            con = DriverManager.getConnection(db_url, db_username, db_password);
            st = con.createStatement();
            String query = null;
            switch(requestModel.getWidget_name()){
                case "tiles":
                    query = "INSERT into widget_tiles (title, image, url, alt_tag, desktop_sort_order, mobile_sort_order, widget_list_id, status, start_date, end_date, device) values ('"+requestModel.getTitle()+ "','"+requestModel.getImage()+"','"+requestModel.getUrl()+"','"+requestModel.getAlt_tag()+"','"+requestModel.getDesktop_sort_order()+"','"+requestModel.getMobile_sort_order()+"','"+requestModel.getWidget_list_id()+"','"+requestModel.getStatus()+"','"+requestModel.getStart_date()+"','"+requestModel.getEnd_date()+"','"+requestModel.getDevice()+"');";
                    break;

                case "lenses":
                case "polaroid":
                    query = "INSERT INTO newigp_widget_polaroid_lenses(title, image_name, url, text, status, sort_order, widget_list_id) VALUES('"+requestModel.getTitle()+"'" + ",'"+requestModel.getImage_name()+"'" + ",'"+requestModel.getUrl()+"'" + ",'"+requestModel.getText()+"'" + ",'"+requestModel.getStatus()+"'" + ",'"+requestModel.getSort_order()+"'" + ",'"+requestModel.getWidget_list_id()+"');";
                    break;

                case "country-city":
                    query = "INSERT INTO newigp_country_city_strips_widget_tmp(fk_associate_id, card_id, type, title, url, image, sort_order, status, device) VALUES('" +requestModel.getFk_associate_id()+"'"+ ",'"+requestModel.getCard_id()+"'" + ",'"+requestModel.getType()+"'" + ",'"+requestModel.getTitle()+"'" + ",'"+requestModel.getUrl()+"'" + ",'"+requestModel.getImage()+"'" + ",'"+requestModel.getSort_order()+"'" + ",'"+requestModel.getStatus()+"'" + ",'"+requestModel.getDevice()+"');";
                    break;

                case "banner":
                case "split-banner":
                case "strip-banner":
                    query ="INSERT INTO homepage_banner_widget_tmp (widget_list_id, cat_id, title, url, ban_group, image, html_text, track_event, status, sort_order, schedule_date, expiry_date, site, device) VALUES('"+requestModel.getWidget_list_id()+"','"+requestModel.getCat_id()+"','"+requestModel.getTitle()+"','"+requestModel.getUrl()+"','"+requestModel.getBan_group()+"','"+requestModel.getImage()+"','"+requestModel.getHtml_text()+"','"+requestModel.getTrack_event()+"','"+requestModel.getStatus()+"','"+requestModel.getSort_order()+"','"+requestModel.getSchedule_date()+"','"+requestModel.getExpiry_date()+"','"+requestModel.getSite()+"','"+requestModel.getDevice()+"')";
                    break;

                case "title":
                    query = "INSERT INTO newigp_widget_title(image_name, title, sub_title, widget_list_id, sort_order, status) VALUES('"+requestModel.getImage_name()+"', '"+requestModel.getTitle()+"', '"+requestModel.getSub_title()+"', '"+requestModel.getWidget_list_id()+"', '"+requestModel.getSort_order()+"', '"+requestModel.getStatus()+"');";
                    break;

                case "reels":
                    query = "INSERT INTO widget_reels (title, display_name, image_url, video_url, redirect_url, button_name, sort_order, status, device, fullscreen) VALUES('" + requestModel.getTitle() + "','" + requestModel.getDisplay_name() + "','" + requestModel.getImage_url() + "','" + requestModel.getVideo_url() + "','" + requestModel.getRedirect_url() + "','" + requestModel.getButton_name() + "','" + requestModel.getSort_order() + "','" + requestModel.getStatus() + "','" + requestModel.getDevice() + "','" + requestModel.getFullscreen() + "');";
                    break;

            }
            response = (st.executeUpdate(query) == 1);

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        finally {
            st.close();
            con.close();

        }
        return response;

    }

    public boolean UpdateHomepageBanner(MainRequestModel requestModel) throws SQLException{
        String db_url = "jdbc:mysql://127.0.0.1:3306/homepage_banner?useSSL=false&serverTimezone=UTC";
        String db_username = "root";
        String db_password = "root";
        boolean response = false;
        Connection con = null;
        Statement st = null;
        try {
            con = DriverManager.getConnection(db_url, db_username, db_password);
            st = con.createStatement();
            String query = null;
            switch(requestModel.getWidget_name()){
                case "tiles":
                    query = "UPDATE widget_tiles SET title = '" + requestModel.getTitle() + "', image = '" + requestModel.getImage() + "', url = '" + requestModel.getUrl() + "', alt_tag = '" + requestModel.getAlt_tag() + "', desktop_sort_order = '"+ requestModel.getDesktop_sort_order() +"', mobile_sort_order = '"+ requestModel.getMobile_sort_order() +"', widget_list_id = '" + requestModel.getWidget_list_id() + "', status = '" + requestModel.getStatus() + "', start_date = '"+ requestModel.getStart_date() +"', end_date = '" + requestModel.getEnd_date() +"', device = '" + requestModel.getDevice() + "' WHERE id = '"+requestModel.getTiles_id()+"';";
                    break;

                case "lenses":
                case "polaroid":
                    query = "UPDATE newigp_widget_polaroid_lenses SET title = '" + requestModel.getTitle() + "', image_name = '" + requestModel.getImage_name() + "', url = '" + requestModel.getUrl() + "', text = '" + requestModel.getText() + "', status = '" + requestModel.getStatus() +"', sort_order = '"+ requestModel.getSort_order() + "', widget_list_id = '" + requestModel.getWidget_list_id() + "' WHERE id = '"+requestModel.getId()+"';";
                    break;

                case "country-city":
                    query = "UPDATE newigp_country_city_strips_widget_tmp SET fk_associate_id='"+requestModel.getFk_associate_id()+"', card_id='"+requestModel.getCard_id()+"', type='"+requestModel.getType()+"', title='"+requestModel.getTitle()+"', url='"+requestModel.getUrl()+"', image='"+requestModel.getImage()+"', sort_order='"+requestModel.getSort_order()+"', status='"+requestModel.getStatus()+"', device='"+requestModel.getDevice()+"', WHERE id= '"+requestModel.getId()+"';";
                    break;

                case "banner":
                case "split-banner":
                case "strip-banner":
                    query ="UPDATE  homepage_banner_widget_tmp SET widget_list_id='"+requestModel.getWidget_list_id()+"', cat_id='"+requestModel.getCat_id()+"', title='"+requestModel.getTitle()+"', url='"+requestModel.getUrl()+"', ban_group='"+requestModel.getBan_group()+"', image='"+requestModel.getImage()+"', html_text='"+requestModel.getHtml_text()+"', track_event='"+requestModel.getTrack_event()+"', status='"+requestModel.getStatus()+"', sort_order='"+requestModel.getSort_order()+"', schedule_date='"+requestModel.getSchedule_date()+"', expiry_date='"+requestModel.getExpiry_date()+"', site='"+requestModel.getSite()+"', device='"+requestModel.getDevice()+"' WHERE new_banner_id="+requestModel.getNew_banner_id()+";";;
                    break;

                case "title":
                    query = "UPDATE newigp_widget_title SET image_name = '"+ requestModel.getImage_name() +"', title = '" + requestModel.getTitle() + "', sub_title = '" + requestModel.getSub_title() + "', widget_list_id = '" + requestModel.getWidget_list_id() + "', sort_order = '" + requestModel.getSort_order() + "', status = '" + requestModel.getStatus() +"' WHERE id = '"+requestModel.getId()+"';";
                    break;

                case "reels":
                    query = "UPDATE widget_reels SET title='" + requestModel.getTitle() + ", display_name='" + requestModel.getDisplay_name() + ", image_url='" + requestModel.getImage_url() + ", video_url='" + requestModel.getVideo_url() + ", redirect_url='" + requestModel.getRedirect_url() + " , button_name='" + requestModel.getButton_name() + ", sort_order='" + requestModel.getSort_order() + ", status='" + requestModel.getStatus() + ", device='" + requestModel.getDevice() + ", widget_list_id = '"+ requestModel.getWidget_list_id() +", fullscreen'" + requestModel.getFullscreen() + "' WHERE id= " + requestModel.getId() + ";";;
                    break;

            }
            response = (st.executeUpdate(query) == 1);

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        finally {
            st.close();
            con.close();

        }
        return response;

    }

}
