package entities;

import formats.Parsetree;

public interface Parser {
    Parsetree parse(String ube);
}
