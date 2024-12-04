// app/services/JobInstanceService.scala
package services

import dao.JobInstanceTable
import models.JobInstance
import slick.jdbc.MySQLProfile.api._
import scala.concurrent.{Future, ExecutionContext}

class JobInstanceService(db: Database)(implicit ec: ExecutionContext) {
  private val jobInstanceQuery = TableQuery[JobInstanceTable]

  def getJobInstancesByJobId(jobId: Int): Future[Seq[JobInstance]] = {
    val query = jobInstanceQuery.filter(_.jobId === jobId)
    db.run(query.result)
  }
}