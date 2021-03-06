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
package com.eucalyptus.auth.euare.common.msgs;

import java.util.Date;
import edu.ucsb.eucalyptus.msgs.EucalyptusData;

public class UserType extends EucalyptusData {

  private String path;
  private String userName;
  private String userId;
  private String arn;
  private Date createDate;
  private String enabled;
  private String passwordExpiration;

  public String getPath( ) {
    return path;
  }

  public void setPath( String path ) {
    this.path = path;
  }

  public String getUserName( ) {
    return userName;
  }

  public void setUserName( String userName ) {
    this.userName = userName;
  }

  public String getUserId( ) {
    return userId;
  }

  public void setUserId( String userId ) {
    this.userId = userId;
  }

  public String getArn( ) {
    return arn;
  }

  public void setArn( String arn ) {
    this.arn = arn;
  }

  public Date getCreateDate( ) {
    return createDate;
  }

  public void setCreateDate( Date createDate ) {
    this.createDate = createDate;
  }

  public String getEnabled( ) {
    return enabled;
  }

  public void setEnabled( String enabled ) {
    this.enabled = enabled;
  }

  public String getPasswordExpiration( ) {
    return passwordExpiration;
  }

  public void setPasswordExpiration( String passwordExpiration ) {
    this.passwordExpiration = passwordExpiration;
  }
}
