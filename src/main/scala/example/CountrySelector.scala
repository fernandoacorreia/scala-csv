package example

class CountrySelector {

  def selectCountriesOnly(parsedData: List[CountryGdpData]): Seq[CountryGdpData] = {
    val regionNames = Seq(
      "Africa Eastern and Southern",
      "Arab World",
      "Central Europe and the Baltics",
      "Early-demographic dividend",
      "East Asia & Pacific (IDA & IBRD countries)",
      "East Asia & Pacific (excluding high income)",
      "East Asia & Pacific",
      "Euro area",
      "Europe & Central Asia (IDA & IBRD countries)",
      "Europe & Central Asia (excluding high income)",
      "Europe & Central Asia",
      "European Union",
      "Fragile and conflict affected situations",
      "Heavily indebted poor countries (HIPC)",
      "High income",
      "IBRD only",
      "IDA & IBRD total",
      "IDA blend",
      "IDA only",
      "IDA total",
      "Late-demographic dividend",
      "Latin America & Caribbean (excluding high income)",
      "Latin America & Caribbean",
      "Latin America & the Caribbean (IDA & IBRD countries)",
      "Least developed countries: UN classification",
      "Low & middle income",
      "Lower middle income",
      "Middle East & North Africa (IDA & IBRD countries)",
      "Middle East & North Africa (excluding high income)",
      "Middle East & North Africa",
      "Middle income",
      "North America",
      "OECD members",
      "Post-demographic dividend",
      "Pre-demographic dividend",
      "Russian Federation",
      "South Asia (IDA & IBRD)",
      "South Asia",
      "Sub-Saharan Africa (IDA & IBRD countries)",
      "Sub-Saharan Africa (excluding high income)",
      "Sub-Saharan Africa",
      "Upper middle income",
      "World",
    )
    parsedData.filterNot(d => regionNames.contains(d.countryName))
  }

}
