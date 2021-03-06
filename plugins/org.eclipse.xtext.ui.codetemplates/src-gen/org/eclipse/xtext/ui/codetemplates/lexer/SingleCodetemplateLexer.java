package org.eclipse.xtext.ui.codetemplates.lexer;

// Use our own Lexer superclass by means of import. 
import org.eclipse.xtext.parser.antlr.Lexer;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class SingleCodetemplateLexer extends Lexer {
    public static final int KEYWORD_7=15;
    public static final int RULE_ID=16;
    public static final int KEYWORD_10=8;
    public static final int KEYWORD_1=9;
    public static final int RULE_STRING=17;
    public static final int KEYWORD_9=7;
    public static final int KEYWORD_2=10;
    public static final int KEYWORD_11=5;
    public static final int KEYWORD_4=12;
    public static final int RULE_WS=18;
    public static final int EOF=-1;
    public static final int KEYWORD_5=13;
    public static final int KEYWORD_6=14;
    public static final int RULE_ANY_OTHER=19;
    public static final int KEYWORD_12=4;
    public static final int KEYWORD_8=6;
    public static final int KEYWORD_3=11;

      private boolean literal = false;
      private boolean templateVariable = false;
      
      public void initialize(boolean literal, boolean templateVariable) {
        this.literal = literal;
        this.templateVariable = templateVariable;
      }


    // delegates
    // delegators

    public SingleCodetemplateLexer() {;} 
    public SingleCodetemplateLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public SingleCodetemplateLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "../org.eclipse.xtext.ui.codetemplates/src/org/eclipse/xtext/ui/codetemplates/lexer/SingleCodetemplateLexer.g"; }

    // $ANTLR start "KEYWORD_12"
    public final void mKEYWORD_12() throws RecognitionException {
        try {
            int _type = KEYWORD_12;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.eclipse.xtext.ui.codetemplates/src/org/eclipse/xtext/ui/codetemplates/lexer/SingleCodetemplateLexer.g:25:12: ({...}? => 'templates' )
            // ../org.eclipse.xtext.ui.codetemplates/src/org/eclipse/xtext/ui/codetemplates/lexer/SingleCodetemplateLexer.g:25:14: {...}? => 'templates'
            {
            if ( !((!literal || templateVariable)) ) {
                throw new FailedPredicateException(input, "KEYWORD_12", "!literal || templateVariable");
            }
            match("templates"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_12"

    // $ANTLR start "KEYWORD_11"
    public final void mKEYWORD_11() throws RecognitionException {
        try {
            int _type = KEYWORD_11;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.eclipse.xtext.ui.codetemplates/src/org/eclipse/xtext/ui/codetemplates/lexer/SingleCodetemplateLexer.g:27:12: ({...}? => 'for' )
            // ../org.eclipse.xtext.ui.codetemplates/src/org/eclipse/xtext/ui/codetemplates/lexer/SingleCodetemplateLexer.g:27:14: {...}? => 'for'
            {
            if ( !((!literal || templateVariable)) ) {
                throw new FailedPredicateException(input, "KEYWORD_11", "!literal || templateVariable");
            }
            match("for"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_11"

    // $ANTLR start "KEYWORD_8"
    public final void mKEYWORD_8() throws RecognitionException {
        try {
            int _type = KEYWORD_8;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.eclipse.xtext.ui.codetemplates/src/org/eclipse/xtext/ui/codetemplates/lexer/SingleCodetemplateLexer.g:29:11: ( '$$' )
            // ../org.eclipse.xtext.ui.codetemplates/src/org/eclipse/xtext/ui/codetemplates/lexer/SingleCodetemplateLexer.g:29:13: '$$'
            {
            match("$$"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_8"

    // $ANTLR start "KEYWORD_9"
    public final void mKEYWORD_9() throws RecognitionException {
        try {
            int _type = KEYWORD_9;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.eclipse.xtext.ui.codetemplates/src/org/eclipse/xtext/ui/codetemplates/lexer/SingleCodetemplateLexer.g:31:11: ( '${' )
            // ../org.eclipse.xtext.ui.codetemplates/src/org/eclipse/xtext/ui/codetemplates/lexer/SingleCodetemplateLexer.g:31:13: '${'
            {
            match("${"); 

            templateVariable = true;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_9"

    // $ANTLR start "KEYWORD_10"
    public final void mKEYWORD_10() throws RecognitionException {
        try {
            int _type = KEYWORD_10;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.eclipse.xtext.ui.codetemplates/src/org/eclipse/xtext/ui/codetemplates/lexer/SingleCodetemplateLexer.g:33:12: ({...}? => '>>' )
            // ../org.eclipse.xtext.ui.codetemplates/src/org/eclipse/xtext/ui/codetemplates/lexer/SingleCodetemplateLexer.g:33:14: {...}? => '>>'
            {
            if ( !((!literal || templateVariable)) ) {
                throw new FailedPredicateException(input, "KEYWORD_10", "!literal || templateVariable");
            }
            match(">>"); 

             if (!literal) literal = true; 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_10"

    // $ANTLR start "KEYWORD_1"
    public final void mKEYWORD_1() throws RecognitionException {
        try {
            int _type = KEYWORD_1;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.eclipse.xtext.ui.codetemplates/src/org/eclipse/xtext/ui/codetemplates/lexer/SingleCodetemplateLexer.g:35:11: ( '$' )
            // ../org.eclipse.xtext.ui.codetemplates/src/org/eclipse/xtext/ui/codetemplates/lexer/SingleCodetemplateLexer.g:35:13: '$'
            {
            match('$'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_1"

    // $ANTLR start "KEYWORD_2"
    public final void mKEYWORD_2() throws RecognitionException {
        try {
            int _type = KEYWORD_2;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.eclipse.xtext.ui.codetemplates/src/org/eclipse/xtext/ui/codetemplates/lexer/SingleCodetemplateLexer.g:37:11: ({...}? => '(' )
            // ../org.eclipse.xtext.ui.codetemplates/src/org/eclipse/xtext/ui/codetemplates/lexer/SingleCodetemplateLexer.g:37:13: {...}? => '('
            {
            if ( !((!literal || templateVariable)) ) {
                throw new FailedPredicateException(input, "KEYWORD_2", "!literal || templateVariable");
            }
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_2"

    // $ANTLR start "KEYWORD_3"
    public final void mKEYWORD_3() throws RecognitionException {
        try {
            int _type = KEYWORD_3;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.eclipse.xtext.ui.codetemplates/src/org/eclipse/xtext/ui/codetemplates/lexer/SingleCodetemplateLexer.g:39:11: ({...}? => ')' )
            // ../org.eclipse.xtext.ui.codetemplates/src/org/eclipse/xtext/ui/codetemplates/lexer/SingleCodetemplateLexer.g:39:13: {...}? => ')'
            {
            if ( !((!literal || templateVariable)) ) {
                throw new FailedPredicateException(input, "KEYWORD_3", "!literal || templateVariable");
            }
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_3"

    // $ANTLR start "KEYWORD_4"
    public final void mKEYWORD_4() throws RecognitionException {
        try {
            int _type = KEYWORD_4;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.eclipse.xtext.ui.codetemplates/src/org/eclipse/xtext/ui/codetemplates/lexer/SingleCodetemplateLexer.g:41:11: ({...}? => ',' )
            // ../org.eclipse.xtext.ui.codetemplates/src/org/eclipse/xtext/ui/codetemplates/lexer/SingleCodetemplateLexer.g:41:13: {...}? => ','
            {
            if ( !((!literal || templateVariable)) ) {
                throw new FailedPredicateException(input, "KEYWORD_4", "!literal || templateVariable");
            }
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_4"

    // $ANTLR start "KEYWORD_5"
    public final void mKEYWORD_5() throws RecognitionException {
        try {
            int _type = KEYWORD_5;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.eclipse.xtext.ui.codetemplates/src/org/eclipse/xtext/ui/codetemplates/lexer/SingleCodetemplateLexer.g:43:11: ({...}? => '.' )
            // ../org.eclipse.xtext.ui.codetemplates/src/org/eclipse/xtext/ui/codetemplates/lexer/SingleCodetemplateLexer.g:43:13: {...}? => '.'
            {
            if ( !((!literal || templateVariable)) ) {
                throw new FailedPredicateException(input, "KEYWORD_5", "!literal || templateVariable");
            }
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_5"

    // $ANTLR start "KEYWORD_6"
    public final void mKEYWORD_6() throws RecognitionException {
        try {
            int _type = KEYWORD_6;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.eclipse.xtext.ui.codetemplates/src/org/eclipse/xtext/ui/codetemplates/lexer/SingleCodetemplateLexer.g:45:11: ({...}? => ':' )
            // ../org.eclipse.xtext.ui.codetemplates/src/org/eclipse/xtext/ui/codetemplates/lexer/SingleCodetemplateLexer.g:45:13: {...}? => ':'
            {
            if ( !((!literal || templateVariable)) ) {
                throw new FailedPredicateException(input, "KEYWORD_6", "!literal || templateVariable");
            }
            match(':'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_6"

    // $ANTLR start "KEYWORD_7"
    public final void mKEYWORD_7() throws RecognitionException {
        try {
            int _type = KEYWORD_7;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.eclipse.xtext.ui.codetemplates/src/org/eclipse/xtext/ui/codetemplates/lexer/SingleCodetemplateLexer.g:47:11: ({...}? => '}' )
            // ../org.eclipse.xtext.ui.codetemplates/src/org/eclipse/xtext/ui/codetemplates/lexer/SingleCodetemplateLexer.g:47:13: {...}? => '}'
            {
            if ( !((!literal || templateVariable)) ) {
                throw new FailedPredicateException(input, "KEYWORD_7", "!literal || templateVariable");
            }
            match('}'); 
            templateVariable = false;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "KEYWORD_7"

    // $ANTLR start "RULE_ID"
    public final void mRULE_ID() throws RecognitionException {
        try {
            int _type = RULE_ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.eclipse.xtext.ui.codetemplates/src/org/eclipse/xtext/ui/codetemplates/lexer/SingleCodetemplateLexer.g:51:9: ( ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )* )
            // ../org.eclipse.xtext.ui.codetemplates/src/org/eclipse/xtext/ui/codetemplates/lexer/SingleCodetemplateLexer.g:51:11: ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            {
            // ../org.eclipse.xtext.ui.codetemplates/src/org/eclipse/xtext/ui/codetemplates/lexer/SingleCodetemplateLexer.g:51:11: ( '^' )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='^') ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // ../org.eclipse.xtext.ui.codetemplates/src/org/eclipse/xtext/ui/codetemplates/lexer/SingleCodetemplateLexer.g:51:11: '^'
                    {
                    match('^'); 

                    }
                    break;

            }

            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // ../org.eclipse.xtext.ui.codetemplates/src/org/eclipse/xtext/ui/codetemplates/lexer/SingleCodetemplateLexer.g:51:40: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='0' && LA2_0<='9')||(LA2_0>='A' && LA2_0<='Z')||LA2_0=='_'||(LA2_0>='a' && LA2_0<='z')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // ../org.eclipse.xtext.ui.codetemplates/src/org/eclipse/xtext/ui/codetemplates/lexer/SingleCodetemplateLexer.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ID"

    // $ANTLR start "RULE_STRING"
    public final void mRULE_STRING() throws RecognitionException {
        try {
            int _type = RULE_STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.eclipse.xtext.ui.codetemplates/src/org/eclipse/xtext/ui/codetemplates/lexer/SingleCodetemplateLexer.g:53:13: ({...}? => ( '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' ) )
            // ../org.eclipse.xtext.ui.codetemplates/src/org/eclipse/xtext/ui/codetemplates/lexer/SingleCodetemplateLexer.g:53:15: {...}? => ( '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )
            {
            if ( !((!literal || templateVariable)) ) {
                throw new FailedPredicateException(input, "RULE_STRING", "!literal || templateVariable");
            }
            // ../org.eclipse.xtext.ui.codetemplates/src/org/eclipse/xtext/ui/codetemplates/lexer/SingleCodetemplateLexer.g:53:48: ( '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )
            // ../org.eclipse.xtext.ui.codetemplates/src/org/eclipse/xtext/ui/codetemplates/lexer/SingleCodetemplateLexer.g:53:50: '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\''
            {
            match('\''); 
            // ../org.eclipse.xtext.ui.codetemplates/src/org/eclipse/xtext/ui/codetemplates/lexer/SingleCodetemplateLexer.g:53:55: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )*
            loop3:
            do {
                int alt3=3;
                int LA3_0 = input.LA(1);

                if ( (LA3_0=='\\') ) {
                    alt3=1;
                }
                else if ( ((LA3_0>='\u0000' && LA3_0<='&')||(LA3_0>='(' && LA3_0<='[')||(LA3_0>=']' && LA3_0<='\uFFFF')) ) {
                    alt3=2;
                }


                switch (alt3) {
            	case 1 :
            	    // ../org.eclipse.xtext.ui.codetemplates/src/org/eclipse/xtext/ui/codetemplates/lexer/SingleCodetemplateLexer.g:53:56: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' )
            	    {
            	    match('\\'); 
            	    if ( input.LA(1)=='\"'||input.LA(1)=='\''||input.LA(1)=='\\'||input.LA(1)=='b'||input.LA(1)=='f'||input.LA(1)=='n'||input.LA(1)=='r'||input.LA(1)=='t' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;
            	case 2 :
            	    // ../org.eclipse.xtext.ui.codetemplates/src/org/eclipse/xtext/ui/codetemplates/lexer/SingleCodetemplateLexer.g:53:97: ~ ( ( '\\\\' | '\\'' ) )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='&')||(input.LA(1)>='(' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

            match('\''); 

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_STRING"

    // $ANTLR start "RULE_WS"
    public final void mRULE_WS() throws RecognitionException {
        try {
            int _type = RULE_WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.eclipse.xtext.ui.codetemplates/src/org/eclipse/xtext/ui/codetemplates/lexer/SingleCodetemplateLexer.g:55:9: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // ../org.eclipse.xtext.ui.codetemplates/src/org/eclipse/xtext/ui/codetemplates/lexer/SingleCodetemplateLexer.g:55:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
            // ../org.eclipse.xtext.ui.codetemplates/src/org/eclipse/xtext/ui/codetemplates/lexer/SingleCodetemplateLexer.g:55:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            int cnt4=0;
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>='\t' && LA4_0<='\n')||LA4_0=='\r'||LA4_0==' ') ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // ../org.eclipse.xtext.ui.codetemplates/src/org/eclipse/xtext/ui/codetemplates/lexer/SingleCodetemplateLexer.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt4 >= 1 ) break loop4;
                        EarlyExitException eee =
                            new EarlyExitException(4, input);
                        throw eee;
                }
                cnt4++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_WS"

    // $ANTLR start "RULE_ANY_OTHER"
    public final void mRULE_ANY_OTHER() throws RecognitionException {
        try {
            int _type = RULE_ANY_OTHER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.eclipse.xtext.ui.codetemplates/src/org/eclipse/xtext/ui/codetemplates/lexer/SingleCodetemplateLexer.g:57:16: ( . )
            // ../org.eclipse.xtext.ui.codetemplates/src/org/eclipse/xtext/ui/codetemplates/lexer/SingleCodetemplateLexer.g:57:18: .
            {
            matchAny(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ANY_OTHER"

    public void mTokens() throws RecognitionException {
        // ../org.eclipse.xtext.ui.codetemplates/src/org/eclipse/xtext/ui/codetemplates/lexer/SingleCodetemplateLexer.g:1:8: ( KEYWORD_12 | KEYWORD_11 | KEYWORD_8 | KEYWORD_9 | KEYWORD_10 | KEYWORD_1 | KEYWORD_2 | KEYWORD_3 | KEYWORD_4 | KEYWORD_5 | KEYWORD_6 | KEYWORD_7 | RULE_ID | RULE_STRING | RULE_WS | RULE_ANY_OTHER )
        int alt5=16;
        alt5 = dfa5.predict(input);
        switch (alt5) {
            case 1 :
                // ../org.eclipse.xtext.ui.codetemplates/src/org/eclipse/xtext/ui/codetemplates/lexer/SingleCodetemplateLexer.g:1:10: KEYWORD_12
                {
                mKEYWORD_12(); 

                }
                break;
            case 2 :
                // ../org.eclipse.xtext.ui.codetemplates/src/org/eclipse/xtext/ui/codetemplates/lexer/SingleCodetemplateLexer.g:1:21: KEYWORD_11
                {
                mKEYWORD_11(); 

                }
                break;
            case 3 :
                // ../org.eclipse.xtext.ui.codetemplates/src/org/eclipse/xtext/ui/codetemplates/lexer/SingleCodetemplateLexer.g:1:32: KEYWORD_8
                {
                mKEYWORD_8(); 

                }
                break;
            case 4 :
                // ../org.eclipse.xtext.ui.codetemplates/src/org/eclipse/xtext/ui/codetemplates/lexer/SingleCodetemplateLexer.g:1:42: KEYWORD_9
                {
                mKEYWORD_9(); 

                }
                break;
            case 5 :
                // ../org.eclipse.xtext.ui.codetemplates/src/org/eclipse/xtext/ui/codetemplates/lexer/SingleCodetemplateLexer.g:1:52: KEYWORD_10
                {
                mKEYWORD_10(); 

                }
                break;
            case 6 :
                // ../org.eclipse.xtext.ui.codetemplates/src/org/eclipse/xtext/ui/codetemplates/lexer/SingleCodetemplateLexer.g:1:63: KEYWORD_1
                {
                mKEYWORD_1(); 

                }
                break;
            case 7 :
                // ../org.eclipse.xtext.ui.codetemplates/src/org/eclipse/xtext/ui/codetemplates/lexer/SingleCodetemplateLexer.g:1:73: KEYWORD_2
                {
                mKEYWORD_2(); 

                }
                break;
            case 8 :
                // ../org.eclipse.xtext.ui.codetemplates/src/org/eclipse/xtext/ui/codetemplates/lexer/SingleCodetemplateLexer.g:1:83: KEYWORD_3
                {
                mKEYWORD_3(); 

                }
                break;
            case 9 :
                // ../org.eclipse.xtext.ui.codetemplates/src/org/eclipse/xtext/ui/codetemplates/lexer/SingleCodetemplateLexer.g:1:93: KEYWORD_4
                {
                mKEYWORD_4(); 

                }
                break;
            case 10 :
                // ../org.eclipse.xtext.ui.codetemplates/src/org/eclipse/xtext/ui/codetemplates/lexer/SingleCodetemplateLexer.g:1:103: KEYWORD_5
                {
                mKEYWORD_5(); 

                }
                break;
            case 11 :
                // ../org.eclipse.xtext.ui.codetemplates/src/org/eclipse/xtext/ui/codetemplates/lexer/SingleCodetemplateLexer.g:1:113: KEYWORD_6
                {
                mKEYWORD_6(); 

                }
                break;
            case 12 :
                // ../org.eclipse.xtext.ui.codetemplates/src/org/eclipse/xtext/ui/codetemplates/lexer/SingleCodetemplateLexer.g:1:123: KEYWORD_7
                {
                mKEYWORD_7(); 

                }
                break;
            case 13 :
                // ../org.eclipse.xtext.ui.codetemplates/src/org/eclipse/xtext/ui/codetemplates/lexer/SingleCodetemplateLexer.g:1:133: RULE_ID
                {
                mRULE_ID(); 

                }
                break;
            case 14 :
                // ../org.eclipse.xtext.ui.codetemplates/src/org/eclipse/xtext/ui/codetemplates/lexer/SingleCodetemplateLexer.g:1:141: RULE_STRING
                {
                mRULE_STRING(); 

                }
                break;
            case 15 :
                // ../org.eclipse.xtext.ui.codetemplates/src/org/eclipse/xtext/ui/codetemplates/lexer/SingleCodetemplateLexer.g:1:153: RULE_WS
                {
                mRULE_WS(); 

                }
                break;
            case 16 :
                // ../org.eclipse.xtext.ui.codetemplates/src/org/eclipse/xtext/ui/codetemplates/lexer/SingleCodetemplateLexer.g:1:161: RULE_ANY_OTHER
                {
                mRULE_ANY_OTHER(); 

                }
                break;

        }

    }


    protected DFA5 dfa5 = new DFA5(this);
    static final String DFA5_eotS =
        "\1\uffff\2\21\1\25\1\17\1\27\1\30\1\31\1\32\1\33\1\34\1\17\1\uffff"+
        "\1\17\2\uffff\1\21\1\uffff\1\21\14\uffff\1\21\1\50\6\uffff\1\21"+
        "\1\uffff\1\21\1\uffff\3\21\1\57\2\uffff";
    static final String DFA5_eofS =
        "\61\uffff";
    static final String DFA5_minS =
        "\1\0\1\145\1\157\1\44\1\76\6\0\1\101\1\uffff\1\0\2\uffff\1\155\1"+
        "\uffff\1\162\4\uffff\6\0\2\uffff\1\160\1\60\6\uffff\1\154\1\0\1"+
        "\141\1\uffff\1\164\1\145\1\163\1\60\1\0\1\uffff";
    static final String DFA5_maxS =
        "\1\uffff\1\145\1\157\1\173\1\76\6\0\1\172\1\uffff\1\uffff\2\uffff"+
        "\1\155\1\uffff\1\162\4\uffff\6\0\2\uffff\1\160\1\172\6\uffff\1\154"+
        "\1\0\1\141\1\uffff\1\164\1\145\1\163\1\172\1\0\1\uffff";
    static final String DFA5_acceptS =
        "\14\uffff\1\15\1\uffff\1\17\1\20\1\uffff\1\15\1\uffff\1\3\1\4\1"+
        "\6\1\5\6\uffff\1\16\1\17\2\uffff\1\7\1\10\1\11\1\12\1\13\1\14\3"+
        "\uffff\1\2\5\uffff\1\1";
    static final String DFA5_specialS =
        "\1\5\3\uffff\1\4\10\uffff\1\2\11\uffff\1\7\1\10\1\1\1\0\1\3\1\6"+
        "\13\uffff\1\12\6\uffff\1\11\1\uffff}>";
    static final String[] DFA5_transitionS = {
            "\11\17\2\16\2\17\1\16\22\17\1\16\3\17\1\3\2\17\1\15\1\5\1\6"+
            "\2\17\1\7\1\17\1\10\13\17\1\11\3\17\1\4\2\17\32\14\3\17\1\13"+
            "\1\14\1\17\5\14\1\2\15\14\1\1\6\14\2\17\1\12\uff82\17",
            "\1\20",
            "\1\22",
            "\1\23\126\uffff\1\24",
            "\1\26",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\32\21\4\uffff\1\21\1\uffff\32\21",
            "",
            "\0\35",
            "",
            "",
            "\1\37",
            "",
            "\1\40",
            "",
            "",
            "",
            "",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            "\1\47",
            "\12\21\7\uffff\32\21\4\uffff\1\21\1\uffff\32\21",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\51",
            "\1\uffff",
            "\1\53",
            "",
            "\1\54",
            "\1\55",
            "\1\56",
            "\12\21\7\uffff\32\21\4\uffff\1\21\1\uffff\32\21",
            "\1\uffff",
            ""
    };

    static final short[] DFA5_eot = DFA.unpackEncodedString(DFA5_eotS);
    static final short[] DFA5_eof = DFA.unpackEncodedString(DFA5_eofS);
    static final char[] DFA5_min = DFA.unpackEncodedStringToUnsignedChars(DFA5_minS);
    static final char[] DFA5_max = DFA.unpackEncodedStringToUnsignedChars(DFA5_maxS);
    static final short[] DFA5_accept = DFA.unpackEncodedString(DFA5_acceptS);
    static final short[] DFA5_special = DFA.unpackEncodedString(DFA5_specialS);
    static final short[][] DFA5_transition;

    static {
        int numStates = DFA5_transitionS.length;
        DFA5_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA5_transition[i] = DFA.unpackEncodedString(DFA5_transitionS[i]);
        }
    }

    class DFA5 extends DFA {

        public DFA5(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 5;
            this.eot = DFA5_eot;
            this.eof = DFA5_eof;
            this.min = DFA5_min;
            this.max = DFA5_max;
            this.accept = DFA5_accept;
            this.special = DFA5_special;
            this.transition = DFA5_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( KEYWORD_12 | KEYWORD_11 | KEYWORD_8 | KEYWORD_9 | KEYWORD_10 | KEYWORD_1 | KEYWORD_2 | KEYWORD_3 | KEYWORD_4 | KEYWORD_5 | KEYWORD_6 | KEYWORD_7 | RULE_ID | RULE_STRING | RULE_WS | RULE_ANY_OTHER );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA5_26 = input.LA(1);

                         
                        int index5_26 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((!literal || templateVariable)) ) {s = 36;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index5_26);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA5_25 = input.LA(1);

                         
                        int index5_25 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((!literal || templateVariable)) ) {s = 35;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index5_25);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA5_13 = input.LA(1);

                         
                        int index5_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((LA5_13>='\u0000' && LA5_13<='\uFFFF')) && ((!literal || templateVariable))) {s = 29;}

                        else s = 15;

                         
                        input.seek(index5_13);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA5_27 = input.LA(1);

                         
                        int index5_27 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((!literal || templateVariable)) ) {s = 37;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index5_27);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA5_4 = input.LA(1);

                         
                        int index5_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA5_4=='>') && ((!literal || templateVariable))) {s = 22;}

                        else s = 15;

                         
                        input.seek(index5_4);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA5_0 = input.LA(1);

                        s = -1;
                        if ( (LA5_0=='t') ) {s = 1;}

                        else if ( (LA5_0=='f') ) {s = 2;}

                        else if ( (LA5_0=='$') ) {s = 3;}

                        else if ( (LA5_0=='>') ) {s = 4;}

                        else if ( (LA5_0=='(') ) {s = 5;}

                        else if ( (LA5_0==')') ) {s = 6;}

                        else if ( (LA5_0==',') ) {s = 7;}

                        else if ( (LA5_0=='.') ) {s = 8;}

                        else if ( (LA5_0==':') ) {s = 9;}

                        else if ( (LA5_0=='}') ) {s = 10;}

                        else if ( (LA5_0=='^') ) {s = 11;}

                        else if ( ((LA5_0>='A' && LA5_0<='Z')||LA5_0=='_'||(LA5_0>='a' && LA5_0<='e')||(LA5_0>='g' && LA5_0<='s')||(LA5_0>='u' && LA5_0<='z')) ) {s = 12;}

                        else if ( (LA5_0=='\'') ) {s = 13;}

                        else if ( ((LA5_0>='\t' && LA5_0<='\n')||LA5_0=='\r'||LA5_0==' ') ) {s = 14;}

                        else if ( ((LA5_0>='\u0000' && LA5_0<='\b')||(LA5_0>='\u000B' && LA5_0<='\f')||(LA5_0>='\u000E' && LA5_0<='\u001F')||(LA5_0>='!' && LA5_0<='#')||(LA5_0>='%' && LA5_0<='&')||(LA5_0>='*' && LA5_0<='+')||LA5_0=='-'||(LA5_0>='/' && LA5_0<='9')||(LA5_0>=';' && LA5_0<='=')||(LA5_0>='?' && LA5_0<='@')||(LA5_0>='[' && LA5_0<=']')||LA5_0=='`'||(LA5_0>='{' && LA5_0<='|')||(LA5_0>='~' && LA5_0<='\uFFFF')) ) {s = 15;}

                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA5_28 = input.LA(1);

                         
                        int index5_28 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((!literal || templateVariable)) ) {s = 38;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index5_28);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA5_23 = input.LA(1);

                         
                        int index5_23 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((!literal || templateVariable)) ) {s = 33;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index5_23);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA5_24 = input.LA(1);

                         
                        int index5_24 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((!literal || templateVariable)) ) {s = 34;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index5_24);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA5_47 = input.LA(1);

                         
                        int index5_47 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((!literal || templateVariable)) ) {s = 48;}

                        else if ( (true) ) {s = 17;}

                         
                        input.seek(index5_47);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA5_40 = input.LA(1);

                         
                        int index5_40 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((!literal || templateVariable)) ) {s = 42;}

                        else if ( (true) ) {s = 17;}

                         
                        input.seek(index5_40);
                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 5, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

}