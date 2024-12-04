// app/MainApp.scala
package main

import java.io._
import play.api.libs.json._
import scala.concurrent.{Await, ExecutionContext}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import slick.jdbc.MySQLProfile.api._
import services.JobInstanceService

object MainApp {
  def main(args: Array[String]): Unit = {
    // Setting up the MySQL database configuration
    val db = Database.forURL(
      "jdbc:mysql://localhost:3306/sit",  // MySQL URL
      user = "root",                     // MySQL username
      password = "",                // MySQL password
      driver = "com.mysql.cj.jdbc.Driver", // MySQL JDBC Driver
      keepAliveConnection = true         // Keep the connection alive
    )

    // Create the JobInstanceService
    val jobInstanceService = new JobInstanceService(db)

    // Example query to fetch job instances where jobId is 2
    val jobInstancesFuture = jobInstanceService.getJobInstancesByJobId(2)

    // Await the result (avoid blocking in production, use callbacks instead)
    try {
      val jobInstances = Await.result(jobInstancesFuture, 10.seconds)

      // Iterate over the job instances and create JSON
      jobInstances.foreach { jobInstance =>
        val jobInstanceJson = Json.obj(
          "JOB_CONFIGURATION (tables used : pls_job_ins_mst)" -> Json.obj(
            "JOB_INSTANCE_ID" -> jobInstance.jobInstanceId,
            "JOB_ID" -> jobInstance.jobId
          )
        )

        // Print the JSON to console
        println(jobInstanceJson)

        // Write the JSON to a file (overwrite if exists)
        val pw = new PrintWriter(new File("job_instance.json"))
        pw.write(jobInstanceJson.toString())
        pw.close()
      }
    } catch {
      case ex: Exception => println(s"An error occurred: ${ex.getMessage}")
    } finally {
      db.close() // Close the database connection
    }
  }
}
