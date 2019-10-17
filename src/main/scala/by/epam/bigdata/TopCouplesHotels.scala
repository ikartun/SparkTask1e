package by.epam.bigdata

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

object TopCouplesHotels {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("HelloSpark").setMaster("local[*]").set("spark.hadoop.validateOutputSpecs", "false")
    val sc = new SparkContext(conf)

    //sc.addJar("/tmp/sparktask1a_2.11-0.1.jar")

    val data = sc.textFile(args(0))                                                     // read from HDFS
      .map(line => line.split(","))                                            // split columns in each row by comma
    val filtered = filter(data)                                                       // filter to get only rows with couples (assuming couples is when srch_adults_cnt >= 2)
    val hotels = filtered.map(x => (x(20) + " " + x(21) + " " + x(22), 1))           // get only hotel composite key and add 1 to calculate count further
    val hotelsCount = hotels.reduceByKey(_ + _)                                     // calculate count of search for each hotel
    val sorted = sortBy(hotelsCount)                                               // sort by count desc
    val Top3 = sorted.take(3)                                              // take needed 3 first records

    sc.parallelize(List((Top3.apply(0)), (Top3.apply(1)), (Top3.apply(2)))).saveAsTextFile(args(1)) //writing to HDFS
    sc.stop()
  }

  def filter(dataset : RDD[Array[String]]) : RDD[Array[String]] = {
    dataset.filter(x => x(13) >= "2")
  }

  def sortBy(kv : RDD[(String, Int)]) : RDD[(String, Int)] = {
    kv.sortBy(_._2, ascending = false)
  }
}
