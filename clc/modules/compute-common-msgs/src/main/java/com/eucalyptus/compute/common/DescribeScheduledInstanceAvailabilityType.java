/*************************************************************************
 * Copyright 2009-2014 Ent. Services Development Corporation LP
 *
 * Redistribution and use of this software in source and binary forms,
 * with or without modification, are permitted provided that the
 * following conditions are met:
 *
 *   Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 *   Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer
 *   in the documentation and/or other materials provided with the
 *   distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 * FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 * COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN
 * ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 ************************************************************************/
package com.eucalyptus.compute.common;

import java.util.ArrayList;
import com.eucalyptus.binding.HttpEmbedded;
import com.eucalyptus.binding.HttpParameterMapping;
import com.google.common.collect.Lists;

public class DescribeScheduledInstanceAvailabilityType extends ScheduledInstanceComputeMessage {

  @HttpParameterMapping( parameter = "Filter" )
  @HttpEmbedded( multiple = true )
  private ArrayList<Filter> filterSet = Lists.newArrayList( );
  private SlotDateTimeRangeRequest firstSlotStartTimeRange;
  private Integer minSlotDurationInHours;
  private Integer maxSlotDurationInHours;
  private ScheduledInstanceRecurrenceRequest recurrence;
  private Integer maxResults;
  private String nextToken;

  public ArrayList<Filter> getFilterSet( ) {
    return filterSet;
  }

  public void setFilterSet( ArrayList<Filter> filterSet ) {
    this.filterSet = filterSet;
  }

  public SlotDateTimeRangeRequest getFirstSlotStartTimeRange( ) {
    return firstSlotStartTimeRange;
  }

  public void setFirstSlotStartTimeRange( SlotDateTimeRangeRequest firstSlotStartTimeRange ) {
    this.firstSlotStartTimeRange = firstSlotStartTimeRange;
  }

  public Integer getMinSlotDurationInHours( ) {
    return minSlotDurationInHours;
  }

  public void setMinSlotDurationInHours( Integer minSlotDurationInHours ) {
    this.minSlotDurationInHours = minSlotDurationInHours;
  }

  public Integer getMaxSlotDurationInHours( ) {
    return maxSlotDurationInHours;
  }

  public void setMaxSlotDurationInHours( Integer maxSlotDurationInHours ) {
    this.maxSlotDurationInHours = maxSlotDurationInHours;
  }

  public ScheduledInstanceRecurrenceRequest getRecurrence( ) {
    return recurrence;
  }

  public void setRecurrence( ScheduledInstanceRecurrenceRequest recurrence ) {
    this.recurrence = recurrence;
  }

  public Integer getMaxResults( ) {
    return maxResults;
  }

  public void setMaxResults( Integer maxResults ) {
    this.maxResults = maxResults;
  }

  public String getNextToken( ) {
    return nextToken;
  }

  public void setNextToken( String nextToken ) {
    this.nextToken = nextToken;
  }
}
