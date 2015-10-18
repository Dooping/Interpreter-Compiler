/* Generated By:JavaCC: Do not edit this line. Parser.java */
import java.util.*;

/** ID lister. */
public class Parser implements ParserConstants {

  /** Main entry point. */
  public static void main(String args[]) {
    Parser parser = new Parser(System.in);
    ASTNode exp;

    while (true) {
    try {
    exp = parser.Start();
   System.out.println( exp.toString() + " = " + exp.eval(new Environ()) );
    } catch (Exception e) {
      System.out.println ("Syntax Error!");
       e.printStackTrace();
      parser.ReInit(System.in);
    }
    }
  }

//ASTNode Start():
//{ ASTNode t; }
//{
//   t = Exp() <EL>
//   {return t; }
//}
  static final public ASTNode Start() throws ParseException {
  ASTNode t;
    t = Exp();
    jj_consume_token(EL);
   {if (true) return t;}
    throw new Error("Missing return statement in function");
  }

/*
ASTNode MinusExp():
{ ASTNode t;
	Token op; }
{	t = Exp() { return t; }
	| <MINUS> t = MinusExp()
		{ 
            t = new ASTSub(new ASTNum(0),t);
         }
    { return t; }
}*/
  static final public ASTNode Exp() throws ParseException {
  Token op;
  ASTNode t1, t2;
    t1 = Term();
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PLUS:
      case MINUS:
        ;
        break;
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PLUS:
        op = jj_consume_token(PLUS);
        break;
      case MINUS:
        op = jj_consume_token(MINUS);
        break;
      default:
        jj_la1[1] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      t2 = Term();
                   if (op.kind == PLUS)
                         t1 = new ASTPlus(t1,t2);
                   else  t1 = new ASTSub(t1,t2);
    }
       {if (true) return t1;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode Term() throws ParseException {
 Token op;
  ASTNode f1, f2;
    f1 = Cmp();
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case TIMES:
      case DIV:
        ;
        break;
      default:
        jj_la1[2] = jj_gen;
        break label_2;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case TIMES:
        op = jj_consume_token(TIMES);
        break;
      case DIV:
        op = jj_consume_token(DIV);
        break;
      default:
        jj_la1[3] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      f2 = Cmp();
                           if (op.kind == TIMES)
                         f1 = new ASTMul(f1,f2);
                   else  f1 = new ASTDiv(f1,f2);
    }
       {if (true) return f1;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode Cmp() throws ParseException {
 Token op;
  ASTNode d1, d2;
    d1 = Bexp();
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case BIGGER:
      case SMALLER:
      case EQUALS:
      case DIF:
        ;
        break;
      default:
        jj_la1[4] = jj_gen;
        break label_3;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case BIGGER:
        op = jj_consume_token(BIGGER);
        break;
      case SMALLER:
        op = jj_consume_token(SMALLER);
        break;
      case EQUALS:
        op = jj_consume_token(EQUALS);
        break;
      case DIF:
        op = jj_consume_token(DIF);
        break;
      default:
        jj_la1[5] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      d2 = Bexp();
                           if (op.kind == BIGGER)
                         {
                            d1 = new ASTBigger(d1,d2);
                          }
                   else if (op.kind == SMALLER)
                   {
                        d1 = new ASTSmaller(d1,d2);
                   }
                    else if (op.kind == EQUALS)
                    {
                      d1 = new ASTEquals(d1,d2);
                    }
                     else if (op.kind == DIF)
                    {
                      d1 = new ASTDif(d1,d2);
                    }
    }
       {if (true) return d1;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode Bexp() throws ParseException {
 Token op;
  ASTNode q1, q2;
    q1 = Fact();
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case AND:
      case OR:
        ;
        break;
      default:
        jj_la1[6] = jj_gen;
        break label_4;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case AND:
        op = jj_consume_token(AND);
        break;
      case OR:
        op = jj_consume_token(OR);
        break;
      default:
        jj_la1[7] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      q2 = Fact();
                           if (op.kind == AND)
                         q1 = new ASTAnd(q1,q2);
                   else  q1 = new ASTOr(q1,q2);
    }
       {if (true) return q1;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode Fact() throws ParseException {
  Token n;
  ASTNode t;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case Num:
      n = jj_consume_token(Num);
             t = new ASTNum(Integer.parseInt(n.image)); {if (true) return t;}
      break;
    case LPAR:
      jj_consume_token(LPAR);
      t = Exp();
      jj_consume_token(RPAR);
                             {if (true) return t;}
      break;
    case Id:
      n = jj_consume_token(Id);
                {if (true) return new ASTId(n.image);}
      break;
    case DECL:
      t = Decl();
                    {if (true) return t;}
      break;
    default:
      jj_la1[8] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode Decl() throws ParseException {
  ASTNode t1,t2;
  Token x;
  ArrayList <Binding> bindings = new ArrayList<Binding>();
    jj_consume_token(DECL);
    label_5:
    while (true) {
      x = jj_consume_token(Id);
      jj_consume_token(ASSOC);
      t1 = Exp();
   bindings.add(new Binding (x.image, t1));
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case Id:
        ;
        break;
      default:
        jj_la1[9] = jj_gen;
        break label_5;
      }
    }
    jj_consume_token(IN);
    t2 = Exp();
    jj_consume_token(END);
   {if (true) return new ASTDecl(bindings,t2);}
    throw new Error("Missing return statement in function");
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public ParserTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[10];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x600,0x600,0x1800,0x1800,0x78000,0x78000,0x180000,0x180000,0x402110,0x400000,};
   }

  /** Constructor with InputStream. */
  public Parser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public Parser(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new ParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 10; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 10; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public Parser(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new ParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 10; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 10; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public Parser(ParserTokenManager tm) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 10; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(ParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 10; i++) jj_la1[i] = -1;
  }

  static private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  static final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  static private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  static private int[] jj_expentry;
  static private int jj_kind = -1;

  /** Generate ParseException. */
  static public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[23];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 10; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 23; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

}
