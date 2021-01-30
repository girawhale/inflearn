package java8.refactoring;

//@MyAnnotation(value = "test")// 변수명이 value라면 생략 가능 ("test")
public class Book {
//    private String a = "a";
//
//    private static String B = "BOOK";
//
//    private static final String C = "BOOK";
//
//    public String d = "d";
//
//    @AnotherAnnotation // 필드에도 Annotation을 붙일 수 있다
//    protected String e = "e";
//
//    public Book() {
//    }
//
//    //    @MyAnnotation 컴파일 에러 발생
//    public Book(String a, String d, String e) {
//        this.a = a;
//        this.d = d;
//        this.e = e;
//    }
//
//    @AnotherAnnotation // 메소드에도 Annotation을 붙일 수 있다
//    private void f() {
//        System.out.println("F");
//    }
//
//    public void g() {
//        System.out.println("g");
//    }
//
//    public Integer h() {
//        return 100;
//    }

    public static String A = "A";

    private String B = "B";

    public Book() {
    }

    public Book(String b) {
        B = b;
    }

    public void c() {
        System.out.println("C");
    }

    public int sum(int left, int right) {
        return left + right;
    }


}
