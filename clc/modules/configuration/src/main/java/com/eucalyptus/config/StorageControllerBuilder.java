package com.eucalyptus.config;

import java.util.List;
import org.apache.log4j.Logger;

import com.eucalyptus.bootstrap.Handles;
import com.eucalyptus.component.Component;
import com.eucalyptus.component.Components;
import com.eucalyptus.component.DatabaseServiceBuilder;
import com.eucalyptus.component.DiscoverableServiceBuilder;
import com.eucalyptus.component.ServiceConfiguration;
import com.eucalyptus.component.ServiceRegistrationException;
import com.eucalyptus.component.id.Storage;
import com.eucalyptus.util.EucalyptusCloudException;
import com.eucalyptus.util.LogUtil;

import edu.ucsb.eucalyptus.msgs.DeregisterStorageControllerType;
import edu.ucsb.eucalyptus.msgs.DescribeStorageControllersType;
import edu.ucsb.eucalyptus.msgs.ModifyStorageControllerAttributeType;
import edu.ucsb.eucalyptus.msgs.RegisterStorageControllerType;

@DiscoverableServiceBuilder( Storage.class )
@Handles( { RegisterStorageControllerType.class, DeregisterStorageControllerType.class, DescribeStorageControllersType.class, StorageControllerConfiguration.class, ModifyStorageControllerAttributeType.class } )
public class StorageControllerBuilder extends DatabaseServiceBuilder<StorageControllerConfiguration> {
  private static Logger LOG = Logger.getLogger( StorageControllerBuilder.class );

  @Override
  public Component getComponent( ) {
    return Components.lookup( com.eucalyptus.bootstrap.Component.storage );
  }
  
  @Override
  public StorageControllerConfiguration newInstance( ) {
    return new StorageControllerConfiguration( );
  }
  
  @Override
  public StorageControllerConfiguration newInstance( String partition, String name, String host, Integer port ) {
    return new StorageControllerConfiguration( partition, name, host, port );
  }
  
  @Override
  public Boolean checkAdd( String partition, String name, String host, Integer port ) throws ServiceRegistrationException {
    try {
      Configuration.getClusterConfiguration( name );
    } catch ( Exception e1 ) {
      throw new ServiceRegistrationException( "Storage controllers may only be registered with a corresponding Cluster of the same name."
                                              + "  No cluster found with the name: " + name );
    }
    return super.checkAdd( partition, name, host, port );
  }

  @Override
  public List<StorageControllerConfiguration> list( ) throws ServiceRegistrationException {
    try {
      return Configuration.getStorageControllerConfigurations( );
    } catch ( EucalyptusCloudException e ) {
      return super.list( );
    }
  }

  @Override
  public Boolean checkRemove( String partition, String name ) throws ServiceRegistrationException {
    return super.checkRemove( partition, name );
  }

  @Override
  public void fireStop( ServiceConfiguration config ) throws ServiceRegistrationException {
    super.fireStop( config );
  }
  
  
  @Override
  public void fireStart( ServiceConfiguration config ) throws ServiceRegistrationException {
    if ( config.isLocal( ) ) {
      java.lang.System.setProperty( "euca.storage.name", config.getName( ) );
      LOG.info( LogUtil.subheader( "Setting euca.storage.name=" + config.getName( ) + " for: " + LogUtil.dumpObject( config ) ) );
    }
    super.fireStart( config );
  }
  
}
