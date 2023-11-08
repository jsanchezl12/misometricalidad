# Tenemos un archivo de logs que contiene errores, warnings y mensajes de información.
# Cada linea del archivo comienza con una letra que indica el tipo de log. Si
# comienza con 'E' es un error, si comienza con 'W' es un warning y si comienza
# con 'I' es de información. Luego de la letra, se encuentra un entero que
# indica el tiempo del log, y luego el mensaje del log. Excepto en el caso de
# los errores, que luego del tipo se encuentra un entero que indica la severidad
# del error. Por ejemplo:

# I 147 iniciando el programa
# W 604 this is not a warning
# E 2 4562 unexpected token

# Queremos separar los errores mas severos (con severidad mayor a 50) y ordenarlos
# cronologicamente. Despues, queremos imprimirlos a la pantalla.

import os

def organizar():
    error_file_path = "./data/error.log"
    logs = []
    with open(error_file_path, "r") as f:
        logs += f.readlines()
    errores = []
    for linea in logs:
        partes = linea.rstrip().split(" ")
        if partes[0] == "E" and int(partes[1]) > 50:
            errores.append(partes)
    errores.sort(key=lambda x: (int(x[2]), int(x[1])))
    for error in errores:
        print(" ".join(error))

def main():
    organizar()

if __name__ == "__main__":
    main()
