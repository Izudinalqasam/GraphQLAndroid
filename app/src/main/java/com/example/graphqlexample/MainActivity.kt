package com.example.graphqlexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Logger.addLogAdapter(AndroidLogAdapter())

        val apolloClientInstance = ApolloClient.builder().serverUrl("https://countries.trevorblades.com/")
            .build()

        apolloClientInstance.query(
            LoginUserQuery.builder().build()
        ).enqueue(object : ApolloCall.Callback<LoginUserQuery.Data>() {

            override fun onFailure(e: ApolloException) {
                Logger.d(e.localizedMessage)
            }

            override fun onResponse(response: Response<LoginUserQuery.Data>) {
               Logger.d(response.data.toString())
            }

        })

        // Note :
        // follow the instruction to create GraphQL schema
        // https://www.apollographql.com/docs/android/essentials/get-started/

        // to make our own mock graphql server
        // https://countries.trevorblades.com/   or
        // Github GraphQL API

    }
}
