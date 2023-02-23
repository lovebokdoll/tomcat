package com.pojo.step1;

public class Pattern {
    
    public static void main( String[] args ) {
        // String url="/chat221228/업무이름/페이지이름 또는 요청이름" ;
        String url     = "/chat221228/dept/getDeptList.kh";
        String context = "chat221228/";
        String command = url.substring( context.length() + 1 );
        //톰캣서버에 요청할 때 사용되는 주소값을 가지고 업무명과 업무에 필요한 이름으로
        //분리시켜 사용자 요청에 따라 처리를 담당할 XXXController객체를 주입하는데 사용함
        System.out.println( command );
        int end = command.lastIndexOf( "." );
        System.out.println( end );
        command = command.substring( 0, end );
        System.out.println( command );
        String upmu[] = null;
        upmu = command.split( "/" );
        
        for ( String imsi : upmu ) {
            System.out.println( imsi );
        }
    }
    
}
