package example

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import java.io.ByteArrayInputStream
import java.nio.charset.StandardCharsets

class GdpExtractorTest extends AnyFlatSpec with Matchers {

  trait Fixture {
    lazy val extractor = new GdpExtractor()
  }

  "extract" should "extract GDP data from an input stream" in new Fixture {
    val fakeData = """|"Country Name","2022","2023",
                      |"Foo","1183962133998.87","1236163044999.97",
                      |"Bar","12345678","987654321",
                      |""".stripMargin
    val is = new ByteArrayInputStream(fakeData.getBytes(StandardCharsets.UTF_8))
    val result = extractor.extract(is)
    val expected = List(
      CountryGdpData("Foo", 1236163044999.97),
      CountryGdpData("Bar", 987654321)
    )
    result should be(expected)
  }

  it should "Handle edge cases in CSV formatting" in new Fixture {
    val fakeData = """|"Country Name","2022","2023",
                      |"Foo","1183962133998.87","1236163044999.97",
                      |"Bar","12345678","987654321",
                      |"Baz, special case","99"
                      |""".stripMargin
    val is = new ByteArrayInputStream(fakeData.getBytes(StandardCharsets.UTF_8))
    val result = extractor.extract(is)
    val expected = List(
      CountryGdpData("Foo", 1236163044999.97),
      CountryGdpData("Bar", 987654321),
      CountryGdpData("Baz, special case", 99)
    )
    result should be(expected)
  }

  it should "Use zero if the GDP is not provided" in new Fixture {
    val fakeData = """|"Country Name","2022","2023",
                      |"Foo"
                      |""".stripMargin
    val is = new ByteArrayInputStream(fakeData.getBytes(StandardCharsets.UTF_8))
    val result = extractor.extract(is)
    val expected = List(
      CountryGdpData("Foo", 0)
    )
    result should be(expected)
  }

  it should "throw an error if the first line does not contain column names" in new Fixture {
    val fakeData = """|"Foo","1183962133998.87","1236163044999.97",
                      |"Bar","12345678","987654321",
                      |""".stripMargin
    val is = new ByteArrayInputStream(fakeData.getBytes(StandardCharsets.UTF_8))
    intercept[MissingColumnNamesRowException] {
      extractor.extract(is)
    }
  }

}
