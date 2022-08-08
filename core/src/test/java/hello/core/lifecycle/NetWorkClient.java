package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetWorkClient {

    private String url;

    public NetWorkClient() {
        System.out.println("생성자 호출, url = " + url);
        connect();
        call("초기화 연결 메시지");
    }

    public NetWorkClient(String url) {
        System.out.println("생성자 호출, url = " + url);
        this.url = url;
        connect();
        call("초기화 연결 메시지");
    }

    public void setUrl(String url) {
        System.out.println("NetWorkClient.setUrl");
        this.url = url;
    }

    // 서비스 시작시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }

    // 서비스 종료시 호출
    public void disconnect() {
        System.out.println("close: " + url);
    }

    @PreDestroy
    public void close() {
        System.out.println("NetWorkClient.destroy");
        disconnect();
    }

    @PostConstruct
    public void init() {
        System.out.println("NetWorkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메시지");
    }
}
