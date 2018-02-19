package com.example.user.barcodereader;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by User on 12/9/2017.
 */

public interface APICall {
    @POST()
    Call<ReturnType> getRespons(@Url String url);
}

/*{
    "status": true,
    "code": 200,
    "message": 123
}*/
