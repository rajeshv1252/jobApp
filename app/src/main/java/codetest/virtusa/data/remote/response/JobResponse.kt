package codetest.virtusa.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class JobResponse(
    @Expose
    @SerializedName("id")
    var id: String?,

    @Expose
    @SerializedName("type")
    var type: String?,

    @Expose
    @SerializedName("created_at")
    var created_at: String?,

    @Expose
    @SerializedName("company")
    var company: String?,

    @Expose
    @SerializedName("location")
    var location: String?,

    @Expose
    @SerializedName("title")
    var title: String?,

    @Expose
    @SerializedName("description")
    var description: String?,

    @Expose
    @SerializedName("company_logo")
    var company_logo: String?
)
