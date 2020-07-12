package test.app.nyt.data.model.nytdata

data class NytData(
    val copyright: String,
    val num_results: Int,
    val results: List<Result>,
    val status: String
)