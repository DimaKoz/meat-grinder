package com.kozhevin.rootchecks.data

import com.kozhevin.rootchecks.annotation.CheckingState



class TotalResult(val list: List<CheckInfo>,
                  @param:CheckingState
                  @field:CheckingState
                  @get:CheckingState
                  val checkState: Int)
