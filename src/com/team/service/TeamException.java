package com.team.service;

import jdk.internal.org.objectweb.asm.util.TraceMethodVisitor;

/**
 * @BelongsProhect:ssss
 * @BelongsPackage:com.team.service
 * @Auther:Chen
 * @CreateTime:2022--06--22 22--46
 * @Decription:T000
 */
public class TeamException extends Exception {
    static final long serialVersionUID = -3387524229948L;

    public TeamException(){
        super();
    }

    public TeamException(String msg){
        super(msg);
    }
}
