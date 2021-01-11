/* A Bison parser, made by GNU Bison 2.3.  */

/* Skeleton interface for Bison's Yacc-like parsers in C

   Copyright (C) 1984, 1989, 1990, 2000, 2001, 2002, 2003, 2004, 2005, 2006
   Free Software Foundation, Inc.

   This program is free software; you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation; either version 2, or (at your option)
   any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program; if not, write to the Free Software
   Foundation, Inc., 51 Franklin Street, Fifth Floor,
   Boston, MA 02110-1301, USA.  */

/* As a special exception, you may create a larger work that contains
   part or all of the Bison parser skeleton and distribute that work
   under terms of your choice, so long as that work isn't itself a
   parser generator using the skeleton or a modified version thereof
   as a parser skeleton.  Alternatively, if you modify or redistribute
   the parser skeleton itself, you may (at your option) remove this
   special exception, which will cause the skeleton and the resulting
   Bison output files to be licensed under the GNU General Public
   License without this special exception.

   This special exception was added by the Free Software Foundation in
   version 2.2 of Bison.  */

/* Tokens.  */
#ifndef YYTOKENTYPE
# define YYTOKENTYPE
   /* Put the tokens into the symbol table, so that GDB and other debuggers
      know about them.  */
   enum yytokentype {
     DO = 258,
     AND = 259,
     OR = 260,
     NOT = 261,
     IF = 262,
     ELSE = 263,
     ELIF = 264,
     WHILE = 265,
     FOR = 266,
     READ = 267,
     WRITE = 268,
     INTEGER = 269,
     STRING = 270,
     CHAR = 271,
     BOOL = 272,
     RETURN = 273,
     FUNCTION = 274,
     IDENTIFIER = 275,
     CONSTANT = 276,
     SEMI_COLON = 277,
     COMMA = 278,
     DOT = 279,
     OPEN_CURLY_BRACKET = 280,
     CLOSED_CURLY_BRACKET = 281,
     OPEN_SQUARE_BRACKET = 282,
     CLOSED_SQUARE_BRACKET = 283,
     OPEN_ROUND_BRACKET = 284,
     CLOSED_ROUND_BRACKET = 285,
     PLUS = 286,
     MINUS = 287,
     MUL = 288,
     DIV = 289,
     PERCENT = 290,
     LT = 291,
     GT = 292,
     LE = 293,
     GE = 294,
     ATRIB = 295,
     EQ = 296,
     NOT_EQ = 297
   };
#endif
/* Tokens.  */
#define DO 258
#define AND 259
#define OR 260
#define NOT 261
#define IF 262
#define ELSE 263
#define ELIF 264
#define WHILE 265
#define FOR 266
#define READ 267
#define WRITE 268
#define INTEGER 269
#define STRING 270
#define CHAR 271
#define BOOL 272
#define RETURN 273
#define FUNCTION 274
#define IDENTIFIER 275
#define CONSTANT 276
#define SEMI_COLON 277
#define COMMA 278
#define DOT 279
#define OPEN_CURLY_BRACKET 280
#define CLOSED_CURLY_BRACKET 281
#define OPEN_SQUARE_BRACKET 282
#define CLOSED_SQUARE_BRACKET 283
#define OPEN_ROUND_BRACKET 284
#define CLOSED_ROUND_BRACKET 285
#define PLUS 286
#define MINUS 287
#define MUL 288
#define DIV 289
#define PERCENT 290
#define LT 291
#define GT 292
#define LE 293
#define GE 294
#define ATRIB 295
#define EQ 296
#define NOT_EQ 297




#if ! defined YYSTYPE && ! defined YYSTYPE_IS_DECLARED
typedef int YYSTYPE;
# define yystype YYSTYPE /* obsolescent; will be withdrawn */
# define YYSTYPE_IS_DECLARED 1
# define YYSTYPE_IS_TRIVIAL 1
#endif

extern YYSTYPE yylval;

