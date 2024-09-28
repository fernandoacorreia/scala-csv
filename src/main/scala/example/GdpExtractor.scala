package example

import java.io.InputStream
import scala.collection.mutable.ArrayBuffer
import scala.io.Source

class GdpExtractor {
  def extract(inputStream: InputStream): List[CountryGdpData] = {
    val lines = Source.fromInputStream(inputStream).getLines()
    skipColumnNamesRow(lines)
    lines.map(extractGdp).toList
  }

  private def skipColumnNamesRow(lines: Iterator[String]): Unit = {
    val line = lines.next()
    if (!line.startsWith("\"Country Name\",")) {
      throw new MissingColumnNamesRowException
    }
  }

  private def extractGdp(line: String): CountryGdpData = {
    val data = parseCsv(line)
    val countryName = data(0)
    CountryGdpData(countryName, getLastGdp(data))
  }

  private def parseCsv(line: String): Array[String] = {
    val a = new ArrayBuffer[String]()
    val regex = """"(.*?)"|([^,]+)""".r
    for (m <- regex.findAllMatchIn(line)) {
      a.append(Option(m.group(1)).getOrElse(m.group(2)))
    }
    a.toArray
  }

  private def getLastGdp(data: Array[String]): Double = {
    val i = data.length - 1
    if (i < 1) {
      return 0
    }
    val lastColumn = data(i)
    if (lastColumn.isEmpty) {
      return 0
    }
    lastColumn.toDouble
  }
}

case class CountryGdpData(
    countryName: String,
    gdp: Double
)

class MissingColumnNamesRowException extends IllegalArgumentException("Column names row is missing.")
