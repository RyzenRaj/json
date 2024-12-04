// app/dao/JobInstanceTable.scala
package dao

import slick.jdbc.MySQLProfile.api._
import models.JobInstance

class JobInstanceTable(tag: Tag) extends Table[JobInstance](tag, "PLS_JOB_INS_MST") {
  def jobInstanceId = column[Int]("JOB_INSTANCE_ID", O.PrimaryKey)
  def jobId = column[Int]("JOB_ID")

  // Mapping the table columns to case class
  def * = (jobInstanceId, jobId) <> (JobInstance.tupled, JobInstance.unapply)
}
