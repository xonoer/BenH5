package org.benmobile.protocol.service;


/**
 * Created by Jekshow on 2016/12/5.
 * 中间件基础服务接口类，该接口将是整个中间件的开始，定义中间件的基础函数，后续扩建基于该接口进行扩展
 */

public interface  Baseinterface {
    //API定义函数，用于定义中间件对外提供的接口的含义、调用方式、参数、返回信息等。
    //控制器调度函数，根据api转义，调用相关的控制器。
    //处理器调度函数，根据控制器转义，调用相关处理器。
    //执行器调度函数，根据处理器转义，调用相关执行器，处理请求。
    //请求action转义函数，处理各种action转义为，中间件可以理解的意图。
    //配置工厂调度函数，根据外部传入数据，获取相关配置信息。
    //核心本地升级处理函数，处理中间件的固件本地升级。
    //核心网络升级处理函数，处理中间件固件的在线网络升级。
    //缓存调度函数，根据传入数据，获取相应的缓存实例。
    //数据加/解密函数，请求参数解密，响应数据加密。
    //请求优先级处理函数，用于设置不同类型的请求的优先级。
    //策略调度函数，根据相关参数，调整中间的运行策略或缓存的策略。
    //自定义异常处理函数，处理中间件自定义的异常。
    //请求失败二次重试函数，处理重试执行失败的请求，再次失败，直接返回结果。
    //数据库引擎调度函数，负责数据库的相关操作的调度工作。
    //模块注册函数，负责完成相关模块的注册工作，一般写入配置工厂或者数据库中的配置文件。
    //缓存模式设置函数，设置常用的缓存模式，目前仅为内存、文件、数据库。
    //网络模块底层实现切换函数，根据系统版本，切换网络模块的底层实现。
    //第三方依赖注册函数，负责第三方依赖的注册，主要包括，名称，版本，主要入口。
    //通信调度函数，负责处理，插件的通信模式下的相关消息。
    //业务调度函数，负责插件的业务层的相关业务处理。
    //基础设施调度函数，负责插件基础能力的调度工作。
    //中间件日志函数，负责记录中间件的运行记录。
    //自定义类加载函数，负责自定义类加载器的调度。
    //资源下载函数，负责中间件代码资源和数据资源的下载工作。
    //配置文件解析函数，负责外部配置文件，解析为中间件可以了解的方式。
    //事件处理函数，负责中间件相关事件触发的包装工作。

}
