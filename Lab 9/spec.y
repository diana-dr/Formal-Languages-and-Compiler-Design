%{
#include <stdio.h>
#include <stdlib.h>

#define YYDEBUG 1 
%}

%token DO
%token AND
%token OR
%token NOT
%token IF
%token ELSE
%token ELIF
%token WHILE
%token FOR
%token READ
%token WRITE
%token INTEGER
%token STRING
%token CHAR
%token BOOL
%token RETURN
%token FUNCTION
%token IDENTIFIER
%token CONSTANT
%token SEMI_COLON
%token COMMA
%token DOT
%token OPEN_CURLY_BRACKET
%token CLOSED_CURLY_BRACKET
%token OPEN_SQUARE_BRACKET
%token CLOSED_SQUARE_BRACKET
%token OPEN_ROUND_BRACKET
%token CLOSED_ROUND_BRACKET
%token PLUS
%token MINUS
%token MUL
%token DIV
%token PERCENT
%token LT
%token GT
%token LE
%token GE
%token ATRIB
%token EQ
%token NOT_EQ

%left '+' '-' '*' '/'


%start program_stmt

%%

program_stmt : FUNCTION compound_stmt
             ;

compound_stmt : OPEN_CURLY_BRACKET stmt_list CLOSED_CURLY_BRACKET
              ;

stmt_list : stmt
          | stmt stmt_list
          ;

stmt : simple_stmt
     | complex_stmt
     ;

simple_stmt : decl_stmt
          | assign_stmt
          | return_stmt 
          ;

complex_stmt : if_stmt
            | loop_stmt
            ;

decl_stmt : type IDENTIFIER NZidentifier
          | type IDENTIFIER ATRIB expression NZEidentifier 
          ;

NZidentifier : COMMA IDENTIFIER NZidentifier 
             | SEMI_COLON
             ;

NZEidentifier : COMMA IDENTIFIER ATRIB expression NZEidentifier 
              | SEMI_COLON
              ;

type : primary_types 
     ;

primary_types : INTEGER
              | CHAR 
              | STRING 
              | BOOL
              ;

assign_stmt : IDENTIFIER ATRIB expression
            ;

expression : term operator expression
           | term
           ;

operator : PLUS
         | MINUS
         ;

term : factor MUL term
     | factor DIV term
     | factor
     ;

factor : OPEN_ROUND_BRACKET expression CLOSED_ROUND_BRACKET
       | IDENTIFIER
       | IDENTIFIER OPEN_SQUARE_BRACKET expression CLOSED_SQUARE_BRACKET
       | CONSTANT
       ;

return_stmt : RETURN expression
            ;

if_stmt : IF condition DO compound_stmt 
        | IF condition DO compound_stmt elif_stmt
	| IF condition AND condition DO compound_stmt
        ;

elif_stmt : ELIF condition DO compound_stmt elif_stmt 
          | ELIF condition DO compound_stmt 
          | ELSE DO compound_stmt
          ;

loop_stmt : for_stmt 
          | while_stmt
          ;

for_stmt : FOR OPEN_ROUND_BRACKET for_first condition SEMI_COLON assign_stmt CLOSED_ROUND_BRACKET compound_stmt 
         | FOR OPEN_ROUND_BRACKET for_first condition CLOSED_ROUND_BRACKET compound_stmt
         ;

for_first : decl_stmt 
          | assign_stmt
          ;

while_stmt : WHILE OPEN_ROUND_BRACKET condition CLOSED_ROUND_BRACKET compound_stmt
           ;

condition : expression relational_operator expression conditional_operator condition 
          | NOT expression relational_operator expression conditional_operator condition 
          | expression relational_operator expression 
          | NOT expression relational_operator expression

relational_operator : GT 
                    | LT 
                    | GE 
                    | LE 
                    | EQ 
                    | NOT_EQ
                    ;

conditional_operator : AND 
                     | OR
                     ;

%%

yyerror(char *s)
{
  printf("%s\n", s);
}

extern FILE *yyin;

int main(int argc, char **argv)
{
  if(argc>1)
    yyin = fopen(argv[1], "r");

  if((argc>2)&&(!strcmp(argv[2],"-d")))
    yydebug = 1;

  if(!yyparse())
    fprintf(stderr,"\tThe input program is valid according to the given grammar rules.\n");
  else
    printf( "The input program is incorrect.\n" );
  return 0;
}
