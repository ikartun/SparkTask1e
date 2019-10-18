package by.epam.bigdata

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import org.scalatest._

/**
  * Created by Igor_Kartun on 5/15/2019.
  */
class UnitTest extends FlatSpec with Matchers{
  val conf = new SparkConf().setAppName("TestSpark").setMaster("local[*]")
  val sc = new SparkContext(conf)

  it should "return count of records = 0 because x(13) < 2" in {
    val data: Seq[Array[String]] = Seq(Array((""), (""), (""), (""), (""), (""), (""), (""), (""), (""), (""), (""), (""), ("1"), ("")))
    val rdd: RDD[Array[String]] = sc.makeRDD(data)
    val count = TopCouplesHotels.filter(rdd).count()
    //assert(count == 0)
    count should be (0)
  }

  it should "return count of records = 1 because x(13) >= 2" in {
    val data: Seq[Array[String]] = Seq(Array((""), (""), (""), (""), (""), (""), (""), (""), (""), (""), (""), (""), (""), ("33"), ("")))
    val rdd: RDD[Array[String]] = sc.makeRDD(data)
    val count = TopCouplesHotels.filter(rdd).count()
    //assert(count == 0)
    count should be (1)
  }

  it should "return the first element as maximum element = 300" in {
    val data: Seq[(String, Int)] = Seq(("a b c", 200), ("b c d", 150), ("c e f", 300), ("d g d", 1))
    val rdd: RDD[(String, Int)] = sc.makeRDD(data)
    val max = TopCouplesHotels.sortBy(rdd).first()._2
    //assert(max == 300)
    max should be (300)
  }

  it should "return the last element as minimum element = 1" in {
    val data: Seq[(String, Int)] = Seq(("a b c", 200), ("b c d", 150), ("c e f", 300), ("d g d", 1))
    val rdd: RDD[(String, Int)] = sc.makeRDD(data)
    val min = TopCouplesHotels.sortBy(rdd).take(4).last._2
    //assert(min == 1)
    min should be (1)
  }

  it should "throw NoSuchElementException because input collection is empty" in {
    a [java.util.NoSuchElementException] should be thrownBy {
      TopCouplesHotels.sortBy(sc.makeRDD(Seq())).take(4).last._2
    }
  }
}
