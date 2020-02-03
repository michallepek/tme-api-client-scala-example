import scala.util.Try
import javax.crypto.Mac
import java.util.Base64
import java.net.URLEncoder
import javax.crypto.spec.SecretKeySpec
import scala.collection.immutable.TreeMap
import java.nio.charset.StandardCharsets.UTF_8

/**
 * API signature recipe
 */
object ApiSignature {
  /**
   * Compute hmac SHA1 from signature base with secret
   */
  def generate(actionUrl: String, secret: String, params: TreeMap[String, String]): String = Try(hmacSha1(
    signatureBase(actionUrl, params),
    secret
  )).getOrElse("")

  /**
   * Standard Java implementation
   */
  private def hmacSha1(signatureBase: String, secret: String): String = {
    val secretKeySpec = new SecretKeySpec(secret.getBytes, "HmacSHA1")
    val mac = Mac.getInstance("HmacSHA1")
    mac.init(secretKeySpec)
    Base64.getEncoder.encodeToString(mac.doFinal(signatureBase.getBytes))
  }

  /**
   * To compute signature base we need encoded action url with encoded parameters
   */
  private def signatureBase(actionUrl: String, params: TreeMap[String, String]): String =
    "POST&" + encodeUrl(actionUrl) + "&" + encodeUrl(paramsToUrlStr(params))

  private def encodeUrl(input: String): String = URLEncoder.encode(input, UTF_8.toString)

  /**
   * We encode key and parameter and escape specific chars!
   */
  private def paramsToUrlStr(params: TreeMap[String, String]): String = params
    .map(x => encodeUrl(x._1) + "=" + encodeUrl(x._2))
    .reduce(_ + "&" + _)
    .replaceAll("[+]", "%20") // Encode + char
    .replaceAll("%7E", "~") // Revert ~ char
}
