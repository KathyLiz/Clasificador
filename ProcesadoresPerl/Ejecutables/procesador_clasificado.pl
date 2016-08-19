use strict;
use warnings;
use UTF8;

#toma el archivo que devuelve la clasificación 
my $contenido = abrir ("resultados.txt");

#toma el texto que no está clasificado y tiene los caracteres ?
my $libro1 = abrir ("libro1.arff");
#Se escribe un array con los resultados del archivo
my @resultados = split('\n', $contenido);

#Se recorre el array y se va reemplazando la clase en la línea de datos de la clasificaciónn
foreach my $resultado (@resultados){
	$libro1 =~ s/(\?)/$resultado/;
}

#Se guarda el archivo arff
guardar("libro2.arff", $libro1);



sub abrir {
	my ($nombre_archivo)= @_;
	local $/;
	open (FILE, $nombre_archivo) or die "No se puede leer el archivo \"$nombre_archivo\" [$!]\n";
	my $contenido = <FILE>;
	close (FILE);

	return $contenido;
}

sub guardar {
	my ($nombre_archivo, $contenido)= @_;
	open (FILE, ">$nombre_archivo") or die "No se puede guardar el archivo \"nombre_archivo\" [$!]\n";
	print FILE $contenido;
	close (FILE);
	print "Archivo guardado exitosamente\n";
}