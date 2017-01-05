package vip.xuanhao.integration.app.common;

/**
 * Created by Xuanhao on 2016/8/18.
 */

public interface Events {

     class ConnectionEvent {

        private boolean isConnection;

        public ConnectionEvent(boolean isConnection) {
            this.isConnection = isConnection;
        }

        public boolean isConnection() {
            return isConnection;
        }

        public void setConnection(boolean connection) {
            isConnection = connection;
        }
    }

}
