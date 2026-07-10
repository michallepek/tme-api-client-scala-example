This repository is maintained for the **deprecated version of the TME API only** and is no longer updated with the latest API documentation.

For the current API version (v2), please refer to the official documentation available:
- via the [**Developer Portal**][developers-website]
- directly at [**documentation page**][documentation-website]

To ensure you are using the latest endpoints, authentication flow, and integration guidelines, always refer to the official TME API v2 documentation.

[developers-website]: https://developers.tme.eu
[documentation-website]: https://api-doc.tme.eu

# tme-api-client-scala-example

Simple TME API Client written in Scala.

```Scala
object Main extends App {
  val apiConnector = new ApiConnector("<YOUR_TOKEN>", "<YOUR_SECRET>")

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
```

## License

MIT
