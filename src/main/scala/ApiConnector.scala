import scalaj.http.Http
import scala.collection.immutable.TreeMap

/**
 * Call TME API and return response String
 */
class ApiConnector(token: String, secret: String) {
  private val apiUrl = "https://api.tme.eu/"
  private val responseType = "json" // json or xml

  /**
   * Added Token and computed API signature to params and call API
   */
  def call(action: String, params: Map[String, String]): String = {
    val actionUrl = generateApiActionUrl(action)
    val postParams = createApiPostParams(actionUrl, params).toSeq

    Http(actionUrl).postForm(postParams).asString.body
  }

  /**
   * We need add token and sort all params to generate signature. Next step is add signature to params
   */
  private def createApiPostParams(actionUrl: String, params: Map[String, String]): TreeMap[String, String] = {
    val sortedParams = TreeMap("Token" -> token) ++ params // We need sorted params to compute signature
    sortedParams ++ Map("ApiSignature" -> ApiSignature.generate(actionUrl, secret, sortedParams))
  }

  /**
   * Complete API request url
   */
  private def generateApiActionUrl(action: String) = apiUrl + action + "." + responseType
}
