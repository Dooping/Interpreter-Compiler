import java.io.PrintWriter;
import java.util.ArrayList;

public class CodeBlock {
	ArrayList<CompilerFrame> frames;
	ArrayList<String> code;
	static int currentLabel = 0;
	
	CodeBlock(){
		code = new ArrayList<String>();
		frames = new ArrayList<CompilerFrame>();
	}
	
	/*
	 * 
	 * [[E1]]D
[[E2]]D
isub
ifgt L1
sipush 0
goto L2
L1: sipush 1
L2:
	 */
	private int labelGenarator(){
		return currentLabel++;
	}
	
	void emit_compMaior(){
		code.add("isub");
		int label = this.labelGenarator();
		code.add("if_icmpgt L_" + label);
		emit_push(0);
		int label2 = this.labelGenarator();
		code.add("goto L_" + label2);
		code.add("L_" + label + ":");
		emit_push(1);
		code.add("L_" + label2+":");
	}
	
	void emit_and(){
		code.add("iand");
	}
	
	void emit_or(){
		code.add("ior");
	}
	
	void emit_push(int n){
		code.add("sipush "+n);
	}
	
	void emit_add(){
		code.add("iadd");
	}
	
	void emit_mul(){
		code.add("imul");
	}
	
	void emit_sub(){
		code.add("isub");
	}
	
	void emit_div(){
		code.add("idiv");
	}
	
	void comment (String comment){
		code.add(";" + comment);
	}
	
	void loadFrame(CompilerFrame env){
		emit_aload();
		code.add("checkcast frame_" + env.getType());
	}
	
	void getFrame(int frame, int father) {
		code.add("getfield frame_" + frame + "/SL Lframe_" + father + ";");
	}
	
	void emit_getfield(int frame, String id, String type){
		code.add("getfield frame_" + frame + "/loc_" + id + " " + type);
	}
	
	void newFrame(int type, CompilerFrame env){
		comment("create a new frame");
		code.add("new frame_" + type);
		code.add("dup");
		code.add("invokespecial frame_" + type + "/<init>()V");
		code.add("dup");	
		if(type != 1){
			emit_aload();
			code.add("putfield frame_" + type + "/SL Lframe_" + env.getAncestor().getType() + ";");
			code.add("dup");
		}
		frames.add(env);
	}
	
	void endFrame(int frame, int father){
		if(frame == 1){
			code.add("aconst_null");
			emit_astore();
		}

	}
	
	void emit_putField(int frame, String id, String type){
		code.add("putfield frame_" + frame + "/loc_" + id + " " + type);
	}
	
	void emit_aload(){
		code.add("aload 1");
	}
	
	void emit_astore(){
		code.add("astore 1");
	}
	
	void emit_dup(){
		code.add("dup");
	}
	
	void dumpHeader(){
		System.out.println(".class public Demo");
		System.out.println(".super java/lang/Object");
		System.out.println("; standard initializer");
		System.out.println(".method public <init>()V");
		System.out.println("   aload_0");
		System.out.println("   invokenonvirtual java/lang/Object/<init>()V");
		System.out.println("   return");
		System.out.println(".end method");
		System.out.println("");
		System.out.println(".method public static main([Ljava/lang/String;)V");
		System.out.println("       ; set limits used by this method");
		System.out.println("       .limit locals 10	");
		System.out.println("       .limit stack 256");
		System.out.println("");
		System.out.println("       ; setup local variables:");
		System.out.println("");
		System.out.println("       ;    1 - the PrintStream object held in java.lang.System.out");
		System.out.println("       getstatic java/lang/System/out Ljava/io/PrintStream;");
		System.out.println("");
		System.out.println("       ; place your bytecodes here");
		System.out.println("       ; START");
	}

	void dumpFooter(){

		System.out.println("	       ; END");
		System.out.println("");
		System.out.println("");
		System.out.println("	       ; convert to String;");
		System.out.println("	       invokestatic java/lang/String/valueOf(I)Ljava/lang/String;");
		System.out.println("	       ; call println ");
		System.out.println("	       invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V");
		System.out.println("");
		System.out.println("	       return");
		System.out.println("");
		System.out.println("	.end method");
	}

	void dumpCode(){
		for (String s : code)
			System.out.println(s);
	}
	
	public void dump(){
		createDemo();
		createFrames();
		createInterface();
	}

	//Criar ficheiros
	void createDemo(){
		try{
			PrintWriter out = new PrintWriter("Demo.j");
			createHeader(out);
			createCode(out);
			createFooter(out);
			out.close();
		} catch (Exception e){
			//e.printStackTrace();
		}
	}
	
	void createHeader(PrintWriter out){
		out.println(".class public Demo");
		out.println(".super java/lang/Object");
		out.println("; standard initializer");
		out.println(".method public <init>()V");
		out.println("   aload_0");
		out.println("   invokenonvirtual java/lang/Object/<init>()V");
		out.println("   return");
		out.println(".end method");
		out.println("");
		out.println(".method public static main([Ljava/lang/String;)V");
		out.println("       ; set limits used by this method");
		out.println("       .limit locals 10	");
		out.println("       .limit stack 256");
		out.println("");
		out.println("       ; setup local variables:");
		out.println("");
		out.println("       ;    1 - the PrintStream object held in java.lang.System.out");
		out.println("       getstatic java/lang/System/out Ljava/io/PrintStream;");
		out.println("");
		out.println("       ; place your bytecodes here");
		out.println("       ; START");
	}

	void createFooter(PrintWriter out){
		out.println("	       ; END");
		out.println("");
		out.println("");
		out.println("	       ; convert to String;");
		out.println("	       invokestatic java/lang/String/valueOf(I)Ljava/lang/String;");
		out.println("	       ; call println ");
		out.println("	       invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V");
		out.println("");
		out.println("	       return");
		out.println("");
		out.println("	.end method");
	}
	
	void createCode (PrintWriter out){
		for (String s : code)
			out.println(s);
	}

	void createFrames(){
		
		for(CompilerFrame frame: frames){
			try{
				PrintWriter out = new PrintWriter("frame_" + frame.getType()+".j");
				 
				out.println(".source frame_" + frame.getType() + ".j");
				out.println(".class frame_" + frame.getType());
				out.println(".super java/lang/Object");
				out.println(".implements frame");
				out.println();
				
				if(frame.getType() != 1){
					out.println(".field public SL Lframe_" + frame.getAncestor().getType() + ";");
					out.println();
				}
				for(String s: frame.getAssoc()){
					out.println(".field public loc_" + s + " I");
				}
				out.println();
				out.println(".method public <init>()V");
				out.println("aload_0");
				out.println("invokespecial java/lang/Object/<init>()V");
				out.println("return");
				out.println(".end method");
			out.close();
			}catch(Exception e){
				//e.printStackTrace();
			}
		}
	}
	
	void createInterface(){
		try{
			PrintWriter out = new PrintWriter("frame.j");
			out.println(".source frame.j");
			out.println(".interface public frame");
			out.println(".super java/lang/Object");
			out.close();
		}catch(Exception e){
			//e.printStackTrace();
		}
	}
}
