package com.eucalyptus.webui.client.activity;

import java.util.logging.Level;
import java.util.logging.Logger;
import com.eucalyptus.webui.client.ClientFactory;
import com.eucalyptus.webui.client.place.ServicePlace;
import com.eucalyptus.webui.client.view.ServiceView;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class ServiceActivity extends AbstractActivity {
  
  public static final String TITLE = "EUCALYPTUS SERVICE COMPONENTS";
  
  private static final Logger LOG = Logger.getLogger( ServiceActivity.class.getName( ) );
  
  private ClientFactory clientFactory;
  private ServicePlace place;
  
  public ServiceActivity( ServicePlace place, ClientFactory clientFactory ) {
    this.place = place;
    this.clientFactory = clientFactory;
  }
  
  @Override
  public void start( AcceptsOneWidget container, EventBus eventBus ) {
    LOG.log( Level.INFO, "Start ServiceActivity" );
    this.clientFactory.getShellView( ).getContentView( ).setContentTitle( TITLE );
    ServiceView view = this.clientFactory.getServiceView( );
    container.setWidget( view );
  }
  
}
