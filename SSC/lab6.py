i = 0
tp = ''
s = ''

def advance():
    global i, tp, s
    i += 1
    if (len(s) <= i):
        return
    else:
        tp = s[i]

def F():
    global tp, i, s
    if (tp == 'a'):
        advance()
    else:
        if (tp == '('):
            advance()
            E()
            if (tp == ')'):
                advance()
            else:
                print("Not accepted\n")
                exit(1)
        else:
            print("\nAccepted")
            exit(1)

def TP():
    global tp, i, s
    if (tp == '*'):
        advance()
        F()
        TP()

def T():
    global tp, i, s
    F()
    TP()

def EP():
    global tp, i, s
    if (tp == '+'):
        advance()
        T()
        EP()

def E():
    global tp, i, s
    T()
    EP()

def main():
    global tp, i, s
    s = input("String? ")
    tp = s[i]
    E()

    if (tp == s[-1]):
        print("\nAccepted")
    else:
        print("\nNOT accepted")
    
    exit(0)

main()