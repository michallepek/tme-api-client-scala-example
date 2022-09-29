# tme-api-client-scala-example

Simple TME API Client written in Scala.

* https://developers.tme.eu/en/
* https://tme.eu/

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
