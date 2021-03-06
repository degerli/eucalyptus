/*************************************************************************
 * Copyright 2008 Regents of the University of California
 * Copyright 2009-2013 Ent. Services Development Corporation LP
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
 * POSSIBILITY OF SUCH DAMAGE. USERS OF THIS SOFTWARE ACKNOWLEDGE
 * THE POSSIBLE PRESENCE OF OTHER OPEN SOURCE LICENSED MATERIAL,
 * COPYRIGHTED MATERIAL OR PATENTED MATERIAL IN THIS SOFTWARE,
 * AND IF ANY SUCH MATERIAL IS DISCOVERED THE PARTY DISCOVERING
 * IT MAY INFORM DR. RICH WOLSKI AT THE UNIVERSITY OF CALIFORNIA,
 * SANTA BARBARA WHO WILL THEN ASCERTAIN THE MOST APPROPRIATE REMEDY,
 * WHICH IN THE REGENTS' DISCRETION MAY INCLUDE, WITHOUT LIMITATION,
 * REPLACEMENT OF THE CODE SO IDENTIFIED, LICENSING OF THE CODE SO
 * IDENTIFIED, OR WITHDRAWAL OF THE CODE CAPABILITY TO THE EXTENT
 * NEEDED TO COMPLY WITH ANY SUCH LICENSES OR RIGHTS.
 ************************************************************************/

package com.eucalyptus.objectstorage.bootstrap;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.eucalyptus.entities.Entities;
import com.eucalyptus.entities.TransactionResource;
import com.eucalyptus.objectstorage.UnitTestSupport;
import com.eucalyptus.objectstorage.entities.ScheduledJob;

/*
 *
 */
public class ObjectStorageSchedulerManagerTest {

  @BeforeClass
  public static void beforeClass() throws Exception {
    UnitTestSupport.setupOsgPersistenceContext();
  }

  @AfterClass
  public static void afterClass() throws Exception {
    UnitTestSupport.tearDownOsgPersistenceContext();
  }

  @Test
  public void testPopulateJobs() throws Exception {
    ObjectStorageSchedulerManager.start();

    // need to make sure that jobs made it to the database
    boolean foundObjReaper = false;
    boolean foundBktReaper = false;
    boolean foundLifecycleProcessor = false;
    List<ScheduledJob> results = null;
    ScheduledJob example = new ScheduledJob();
    try (TransactionResource tran = Entities.transactionFor(ScheduledJob.class)) {
      results = Entities.query(example);
      tran.commit();
    }
    for (ScheduledJob job : results) {
      if (!foundBktReaper) {
        if (job.getJobClassName().equals(ObjectStorageSchedulerManager.BUCKET_REAPER_CLASSNAME)) {
          foundBktReaper = true;
        }
      }
      if (!foundObjReaper) {
        if (job.getJobClassName().equals(ObjectStorageSchedulerManager.OBJECT_REAPER_CLASSNAME)) {
          foundObjReaper = true;
        }
      }
      if (!foundLifecycleProcessor) {
        if (job.getJobClassName().equals(ObjectStorageSchedulerManager.LIFECYCLE_CLEANUP_CLASSNAME)) {
          foundLifecycleProcessor = true;
        }
      }
    }

    assertTrue("expected to find bucket reaper", foundBktReaper);
    assertTrue("expected to find object reaper", foundObjReaper);
    assertTrue("expected to find lifecycle processor", foundLifecycleProcessor);

    ObjectStorageSchedulerManager.dumpInfo();
    ObjectStorageSchedulerManager.stop();

  }

}
