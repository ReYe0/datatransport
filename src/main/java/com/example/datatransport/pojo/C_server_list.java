package com.example.datatransport.pojo;

// 上级服务器表
public class C_server_list implements java.io.Serializable {

    private static final long serialVersionUID = 6982626322308811030L;
    private Integer id;//
    private String name;// 服务器名称
    private String ip;// ip
    private String port;
    private Byte if_proxy;// 是否使用代理,0:不使用，1:使用
    private String proxy_host;// 代理地址
    private String proxy_port;// 代理端口
    private String user;// 用户
    private String password;// 密码
    private Byte auth;// 认证方式，0:tcp,1:http,2:https
    private int synchro;//设置同步,0:未同步，1:同步成功
    private String proxy_domain;//域

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Byte getIf_proxy() {
        return if_proxy;
    }

    public void setIf_proxy(Byte if_proxy) {
        this.if_proxy = if_proxy;
    }

    public String getProxy_host() {
        return proxy_host;
    }

    public void setProxy_host(String proxy_host) {
        this.proxy_host = proxy_host;
    }


    public String getProxy_port() {
        return proxy_port;
    }

    public void setProxy_port(String proxy_port) {
        this.proxy_port = proxy_port;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getProxy_domain() {
        return proxy_domain;
    }

    public void setProxy_domain(String proxy_domain) {
        this.proxy_domain = proxy_domain;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Byte getAuth() {
        return auth;
    }

    public void setAuth(Byte auth) {
        this.auth = auth;
    }

    public int getSynchro() {
        return synchro;
    }

    public void setSynchro(int synchro) {
        this.synchro = synchro;
    }

}