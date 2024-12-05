// app/services/JobInstanceService.scala
package services

import dao.JobInstanceTable
import models.JobInstance
import slick.jdbc.MySQLProfile.api._
import scala.concurrent.{Future, ExecutionContext}

class JobInstanceService(db: Database)(implicit ec: ExecutionContext) {
  private val jobInstanceQuery = TableQuery[JobInstanceTable]


  // fetch rows based on filter
  def getJobInstancesByJobId(jobId: Int): Future[Seq[JobInstance]] = {
    val query = jobInstanceQuery.filter(_.jobId === jobId)
    db.run(query.result)
  }

  // fetch all rows
  def getAllJobInstances(): Future[Seq[JobInstance]] = {
    db.run(jobInstanceQuery.result)
  }
}
