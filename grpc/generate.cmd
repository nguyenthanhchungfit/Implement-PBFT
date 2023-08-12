echo 'Remove old generated code'
rm -rf com

echo 'generate new source'
protoc --plugin=protoc-gen-grpc-java=/home/chungnt/program/protoc-gen-grpc-java-1.56.1-linux-x86_64.exe \
        --grpc-java_out=. \
        --java_out=. tendermint_consensus_protocol.proto

echo 'move generated code to src folder'
cp -r com/pbft/tendermint/* ../src/com/pbft/tendermint/

rm -rf com