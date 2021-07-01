package kr.or.ddit.enumtype;

public enum OperatorType {
	PLUS('+', new RealOperator() {
		public double operate(double left, double right) {
			return left + right;
		}
	}),
	MINUS('-', (left, right)->{ return left - right;}),
	MULTIPLY('*', (left, right)->{ return left * right;}),
	DIVIDE('/', (left, right)->{ return left / right;});
	
	@FunctionalInterface //이게 있어야 람다식 사용 가능하고 반드시 추상메서드는 하나만 있어야 함
	public static interface RealOperator{
		public double operate(double left, double right);
	}
	
	private char sign;
	private RealOperator realOperator;

	private OperatorType(char sign, RealOperator realOperator) {
		this.sign = sign;
		this.realOperator = realOperator;
	}
	
	public double operate(double left, double right) {
		return realOperator.operate(left, right);
	}
	
	public char getSign(){
		return sign;
	}
	private static final String EXPPTRN = "%f %c %f = %f";
	public String getExpression(double left, double right){
		return String.format(EXPPTRN, left, sign, right, operate(left, right));
	}
	
}
