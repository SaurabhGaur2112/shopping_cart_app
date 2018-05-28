package com.example.gaursaurabh.shoppingcart.Product;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductGetValue {

    public static String[] getProdValue(String id){

        InputStream is = null;
        String line = null;
        String result = null;
        String temp_name = "", temp_price = "", temp_image_first = "", temp_image_second = "", temp_image_third = "";

        String[] arr_name, arr_price, arr_image_first, arr_image_second, arr_image_third;

        String get_name = null, get_price = null, get_imagefirst = null, get_imagesecond = null, get_imagethird = null;

        List<NameValuePair> productvalue = new ArrayList<NameValuePair>(1);
        productvalue.add(new BasicNameValuePair("id", id));

        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://wallnit.com/shopping/product.php   ");
            httpPost.setEntity(new UrlEncodedFormEntity(productvalue));
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
        } catch (Exception e) {

        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
            StringBuffer sb = new StringBuffer();
            while ((line = reader.readLine()) != null)
                sb.append(line + "\n");
            result = sb.toString();
            is.close();
        } catch (Exception e) {

        }

        try {
            JSONArray jArray = new JSONArray(result);
            int count = jArray.length();

            for (int i = 0; i < count; i++) {

                JSONObject json_data = jArray.getJSONObject(i);
                temp_name += json_data.getString("actor_name") + ":";
                temp_price += json_data.getString("actor_name") + ":";
                temp_image_first += json_data.getString("actor_name") + ":";
                temp_image_second += json_data.getString("actor_name") + ":";
                temp_image_third += json_data.getString("actor_name") + ":";
            }

            arr_name = temp_name.split(":");
            arr_price = temp_price.split(":");
            arr_image_first = temp_image_first.split(":");
            arr_image_second = temp_image_second.split(":");
            arr_image_third = temp_image_third.split(":");

            get_name = Arrays.toString(arr_name).replace("[", "").replace("]", "");
            get_price = Arrays.toString(arr_price).replace("[", "").replace("]", "");
            get_imagefirst = Arrays.toString(arr_image_first).replace("[", "").replace("]", "");
            get_imagesecond = Arrays.toString(arr_image_second).replace("[", "").replace("]", "");
            get_imagethird = Arrays.toString(arr_image_third).replace("[", "").replace("]", "");


        } catch (Exception e) {

        }

        return new String[]{
            get_name, get_price, get_imagefirst, get_imagesecond, get_imagethird
        };
    }
}
