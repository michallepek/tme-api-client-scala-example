import org.scalatest.flatspec.AnyFlatSpec
import scala.collection.immutable.TreeMap

class ApiSignatureSpec extends AnyFlatSpec {
  "generate" should "be equal qnvlqHSHb0/3rqvD0A9O8ADolxY=" in {
    val signature = ApiSignature.generate(
      "https://api.tme.eu/Products/Search.json",
      "TEST_SECRET",
      TreeMap(
        "Token" -> "TEST_TOKEN",
        "SearchPlain" -> "multimetr",
        "SearchCategory" -> "100164",
        "SearchParameter[2][0]" -> "386527",
        "SearchParameter[2][1]" -> "15",
        "Country" -> "pl",
        "Language" -> "pl",
      )
    )

    assert(signature === "qnvlqHSHb0/3rqvD0A9O8ADolxY=")
  }
}
