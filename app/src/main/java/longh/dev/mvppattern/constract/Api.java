package longh.dev.mvppattern.constract;

import java.util.List;

import longh.dev.mvppattern.data.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Api {

        // Lấy tất cả các account
        @GET("user")
        Call<List<User>> getAllAccounts();

        // Lấy account theo ID
        @GET("user/{id}")
        Call<User> getAccountById(@Path("id") int id);

        // Tạo một account mới
        @POST("user")
        Call<User> createAccount(@Body User user);

        // cập nhật thông tin account theo ID
        @PUT("user/{id}")
        Call<User> updateAccountById(@Path("id") int id, @Body User user);

        // xoá account theo ID
        @DELETE("user/{id}")
        Call<Void> deleteAccountById(@Path("id") int id);

}
