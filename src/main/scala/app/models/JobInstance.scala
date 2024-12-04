// app/models/JobInstance.scala
package models

import play.api.libs.json._

case class JobInstance(jobInstanceId: Int, jobId: Int)

object JobInstance {
  def tupled = (JobInstance.apply _).tupled

  // Implicit format for JobInstance (no timestamp)
  implicit val jobInstanceFormat: OFormat[JobInstance] = Json.format[JobInstance]
}
