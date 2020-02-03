/**
 * Example TME API call
 */
object Main extends App {
  /**
   * @see https://developers.tme.eu/en/
   */
  val secret = "<YOUR_SECRET>"
  val token = "<YOUR_TOKEN>"

  val apiConnector = new ApiConnector(secret, token)

  val apiResponse = apiConnector.call(
    "Products/GetPrices",
    Map(
      "SymbolList[0]" -> "1N4007-DC",
      "Country" -> "PL",
      "Currency" -> "PLN",
      "Language" -> "PL"
    )
  )

  println(apiResponse)
}
