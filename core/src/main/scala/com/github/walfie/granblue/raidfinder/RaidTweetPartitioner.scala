package com.github.walfie.granblue.raidfinder

import akka.agent.Agent
import com.github.walfie.granblue.raidfinder.util.{ObservablesPartitioner, CachedObservablesPartitioner}
import com.github.walfie.granblue.raidfinder.domain._
import monix.execution.{Ack, Cancelable, Scheduler}
import monix.reactive._
import monix.reactive.observables.GroupedObservable
import monix.reactive.observers.Subscriber
import monix.reactive.subjects.PublishSubject
import scala.concurrent.{ExecutionContext, Future}

object RaidTweetsPartitioner {
  type RaidTweetsPartitioner = ObservablesPartitioner[BossName, RaidTweet]
}

object CachedRaidTweetsPartitioner {
  type CachedRaidTweetsPartitioner = CachedObservablesPartitioner[BossName, RaidTweet]

  def fromObservable(
    observable:       Observable[RaidTweet],
    cacheSizePerBoss: Int
  )(implicit scheduler: Scheduler): CachedRaidTweetsPartitioner = {
    CachedObservablesPartitioner.fromObservable(observable, cacheSizePerBoss)(_.bossName)
  }
}

