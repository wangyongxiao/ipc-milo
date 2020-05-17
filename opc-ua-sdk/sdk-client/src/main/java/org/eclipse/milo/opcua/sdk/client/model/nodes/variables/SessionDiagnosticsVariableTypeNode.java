package org.eclipse.milo.opcua.sdk.client.model.nodes.variables;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.SessionDiagnosticsVariableType;
import org.eclipse.milo.opcua.sdk.client.nodes.UaNode;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.eclipse.milo.opcua.stack.core.types.structured.ApplicationDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.ServiceCounterDataType;
import org.eclipse.milo.opcua.stack.core.util.FutureUtils;
import org.eclipse.milo.opcua.stack.core.util.Unit;

public class SessionDiagnosticsVariableTypeNode extends BaseDataVariableTypeNode implements SessionDiagnosticsVariableType {
    public SessionDiagnosticsVariableTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
                                              QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                              UInteger writeMask, UInteger userWriteMask, DataValue value, NodeId dataType, int valueRank,
                                              UInteger[] arrayDimensions, UByte accessLevel, UByte userAccessLevel,
                                              double minimumSamplingInterval, boolean historizing) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, value, dataType, valueRank, arrayDimensions, accessLevel, userAccessLevel, minimumSamplingInterval, historizing);
    }

    @Override
    public NodeId getSessionId() throws UaException {
        BaseDataVariableTypeNode node = getSessionIdNode();
        return (NodeId) node.getValue().getValue().getValue();
    }

    @Override
    public void setSessionId(NodeId sessionId) throws UaException {
        BaseDataVariableTypeNode node = getSessionIdNode();
        node.setValue(new Variant(sessionId));
    }

    @Override
    public NodeId readSessionId() throws UaException {
        try {
            return readSessionIdAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeSessionId(NodeId sessionId) throws UaException {
        try {
            writeSessionIdAsync(sessionId).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends NodeId> readSessionIdAsync() {
        return getSessionIdNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (NodeId) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeSessionIdAsync(NodeId sessionId) {
        DataValue value = DataValue.valueOnly(new Variant(sessionId));
        return getSessionIdNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value))
            .thenCompose(statusCode -> {
                if (statusCode != null && statusCode.isBad()) {
                    return FutureUtils.failedUaFuture(statusCode);
                } else {
                    return CompletableFuture.completedFuture(Unit.VALUE);
                }
            });
    }

    @Override
    public BaseDataVariableTypeNode getSessionIdNode() throws UaException {
        try {
            return getSessionIdNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getSessionIdNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "SessionId", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=63"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public String getSessionName() throws UaException {
        BaseDataVariableTypeNode node = getSessionNameNode();
        return (String) node.getValue().getValue().getValue();
    }

    @Override
    public void setSessionName(String sessionName) throws UaException {
        BaseDataVariableTypeNode node = getSessionNameNode();
        node.setValue(new Variant(sessionName));
    }

    @Override
    public String readSessionName() throws UaException {
        try {
            return readSessionNameAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeSessionName(String sessionName) throws UaException {
        try {
            writeSessionNameAsync(sessionName).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends String> readSessionNameAsync() {
        return getSessionNameNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (String) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeSessionNameAsync(String sessionName) {
        DataValue value = DataValue.valueOnly(new Variant(sessionName));
        return getSessionNameNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value))
            .thenCompose(statusCode -> {
                if (statusCode != null && statusCode.isBad()) {
                    return FutureUtils.failedUaFuture(statusCode);
                } else {
                    return CompletableFuture.completedFuture(Unit.VALUE);
                }
            });
    }

    @Override
    public BaseDataVariableTypeNode getSessionNameNode() throws UaException {
        try {
            return getSessionNameNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getSessionNameNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "SessionName", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=63"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public ApplicationDescription getClientDescription() throws UaException {
        BaseDataVariableTypeNode node = getClientDescriptionNode();
        return (ApplicationDescription) node.getValue().getValue().getValue();
    }

    @Override
    public void setClientDescription(ApplicationDescription clientDescription) throws UaException {
        BaseDataVariableTypeNode node = getClientDescriptionNode();
        node.setValue(new Variant(clientDescription));
    }

    @Override
    public ApplicationDescription readClientDescription() throws UaException {
        try {
            return readClientDescriptionAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeClientDescription(ApplicationDescription clientDescription) throws UaException {
        try {
            writeClientDescriptionAsync(clientDescription).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ApplicationDescription> readClientDescriptionAsync() {
        return getClientDescriptionNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (ApplicationDescription) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeClientDescriptionAsync(
        ApplicationDescription clientDescription) {
        DataValue value = DataValue.valueOnly(new Variant(clientDescription));
        return getClientDescriptionNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value))
            .thenCompose(statusCode -> {
                if (statusCode != null && statusCode.isBad()) {
                    return FutureUtils.failedUaFuture(statusCode);
                } else {
                    return CompletableFuture.completedFuture(Unit.VALUE);
                }
            });
    }

    @Override
    public BaseDataVariableTypeNode getClientDescriptionNode() throws UaException {
        try {
            return getClientDescriptionNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getClientDescriptionNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "ClientDescription", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=63"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public String getServerUri() throws UaException {
        BaseDataVariableTypeNode node = getServerUriNode();
        return (String) node.getValue().getValue().getValue();
    }

    @Override
    public void setServerUri(String serverUri) throws UaException {
        BaseDataVariableTypeNode node = getServerUriNode();
        node.setValue(new Variant(serverUri));
    }

    @Override
    public String readServerUri() throws UaException {
        try {
            return readServerUriAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeServerUri(String serverUri) throws UaException {
        try {
            writeServerUriAsync(serverUri).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends String> readServerUriAsync() {
        return getServerUriNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (String) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeServerUriAsync(String serverUri) {
        DataValue value = DataValue.valueOnly(new Variant(serverUri));
        return getServerUriNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value))
            .thenCompose(statusCode -> {
                if (statusCode != null && statusCode.isBad()) {
                    return FutureUtils.failedUaFuture(statusCode);
                } else {
                    return CompletableFuture.completedFuture(Unit.VALUE);
                }
            });
    }

    @Override
    public BaseDataVariableTypeNode getServerUriNode() throws UaException {
        try {
            return getServerUriNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getServerUriNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "ServerUri", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=63"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public String getEndpointUrl() throws UaException {
        BaseDataVariableTypeNode node = getEndpointUrlNode();
        return (String) node.getValue().getValue().getValue();
    }

    @Override
    public void setEndpointUrl(String endpointUrl) throws UaException {
        BaseDataVariableTypeNode node = getEndpointUrlNode();
        node.setValue(new Variant(endpointUrl));
    }

    @Override
    public String readEndpointUrl() throws UaException {
        try {
            return readEndpointUrlAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeEndpointUrl(String endpointUrl) throws UaException {
        try {
            writeEndpointUrlAsync(endpointUrl).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends String> readEndpointUrlAsync() {
        return getEndpointUrlNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (String) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeEndpointUrlAsync(String endpointUrl) {
        DataValue value = DataValue.valueOnly(new Variant(endpointUrl));
        return getEndpointUrlNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value))
            .thenCompose(statusCode -> {
                if (statusCode != null && statusCode.isBad()) {
                    return FutureUtils.failedUaFuture(statusCode);
                } else {
                    return CompletableFuture.completedFuture(Unit.VALUE);
                }
            });
    }

    @Override
    public BaseDataVariableTypeNode getEndpointUrlNode() throws UaException {
        try {
            return getEndpointUrlNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getEndpointUrlNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "EndpointUrl", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=63"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public String[] getLocaleIds() throws UaException {
        BaseDataVariableTypeNode node = getLocaleIdsNode();
        return (String[]) node.getValue().getValue().getValue();
    }

    @Override
    public void setLocaleIds(String[] localeIds) throws UaException {
        BaseDataVariableTypeNode node = getLocaleIdsNode();
        node.setValue(new Variant(localeIds));
    }

    @Override
    public String[] readLocaleIds() throws UaException {
        try {
            return readLocaleIdsAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeLocaleIds(String[] localeIds) throws UaException {
        try {
            writeLocaleIdsAsync(localeIds).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends String[]> readLocaleIdsAsync() {
        return getLocaleIdsNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (String[]) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeLocaleIdsAsync(String[] localeIds) {
        DataValue value = DataValue.valueOnly(new Variant(localeIds));
        return getLocaleIdsNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value))
            .thenCompose(statusCode -> {
                if (statusCode != null && statusCode.isBad()) {
                    return FutureUtils.failedUaFuture(statusCode);
                } else {
                    return CompletableFuture.completedFuture(Unit.VALUE);
                }
            });
    }

    @Override
    public BaseDataVariableTypeNode getLocaleIdsNode() throws UaException {
        try {
            return getLocaleIdsNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getLocaleIdsNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "LocaleIds", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=63"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public Double getActualSessionTimeout() throws UaException {
        BaseDataVariableTypeNode node = getActualSessionTimeoutNode();
        return (Double) node.getValue().getValue().getValue();
    }

    @Override
    public void setActualSessionTimeout(Double actualSessionTimeout) throws UaException {
        BaseDataVariableTypeNode node = getActualSessionTimeoutNode();
        node.setValue(new Variant(actualSessionTimeout));
    }

    @Override
    public Double readActualSessionTimeout() throws UaException {
        try {
            return readActualSessionTimeoutAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeActualSessionTimeout(Double actualSessionTimeout) throws UaException {
        try {
            writeActualSessionTimeoutAsync(actualSessionTimeout).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends Double> readActualSessionTimeoutAsync() {
        return getActualSessionTimeoutNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (Double) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeActualSessionTimeoutAsync(Double actualSessionTimeout) {
        DataValue value = DataValue.valueOnly(new Variant(actualSessionTimeout));
        return getActualSessionTimeoutNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value))
            .thenCompose(statusCode -> {
                if (statusCode != null && statusCode.isBad()) {
                    return FutureUtils.failedUaFuture(statusCode);
                } else {
                    return CompletableFuture.completedFuture(Unit.VALUE);
                }
            });
    }

    @Override
    public BaseDataVariableTypeNode getActualSessionTimeoutNode() throws UaException {
        try {
            return getActualSessionTimeoutNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getActualSessionTimeoutNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "ActualSessionTimeout", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=63"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public UInteger getMaxResponseMessageSize() throws UaException {
        BaseDataVariableTypeNode node = getMaxResponseMessageSizeNode();
        return (UInteger) node.getValue().getValue().getValue();
    }

    @Override
    public void setMaxResponseMessageSize(UInteger maxResponseMessageSize) throws UaException {
        BaseDataVariableTypeNode node = getMaxResponseMessageSizeNode();
        node.setValue(new Variant(maxResponseMessageSize));
    }

    @Override
    public UInteger readMaxResponseMessageSize() throws UaException {
        try {
            return readMaxResponseMessageSizeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeMaxResponseMessageSize(UInteger maxResponseMessageSize) throws UaException {
        try {
            writeMaxResponseMessageSizeAsync(maxResponseMessageSize).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UInteger> readMaxResponseMessageSizeAsync() {
        return getMaxResponseMessageSizeNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeMaxResponseMessageSizeAsync(UInteger maxResponseMessageSize) {
        DataValue value = DataValue.valueOnly(new Variant(maxResponseMessageSize));
        return getMaxResponseMessageSizeNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value))
            .thenCompose(statusCode -> {
                if (statusCode != null && statusCode.isBad()) {
                    return FutureUtils.failedUaFuture(statusCode);
                } else {
                    return CompletableFuture.completedFuture(Unit.VALUE);
                }
            });
    }

    @Override
    public BaseDataVariableTypeNode getMaxResponseMessageSizeNode() throws UaException {
        try {
            return getMaxResponseMessageSizeNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getMaxResponseMessageSizeNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "MaxResponseMessageSize", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=63"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public DateTime getClientConnectionTime() throws UaException {
        BaseDataVariableTypeNode node = getClientConnectionTimeNode();
        return (DateTime) node.getValue().getValue().getValue();
    }

    @Override
    public void setClientConnectionTime(DateTime clientConnectionTime) throws UaException {
        BaseDataVariableTypeNode node = getClientConnectionTimeNode();
        node.setValue(new Variant(clientConnectionTime));
    }

    @Override
    public DateTime readClientConnectionTime() throws UaException {
        try {
            return readClientConnectionTimeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeClientConnectionTime(DateTime clientConnectionTime) throws UaException {
        try {
            writeClientConnectionTimeAsync(clientConnectionTime).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends DateTime> readClientConnectionTimeAsync() {
        return getClientConnectionTimeNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (DateTime) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeClientConnectionTimeAsync(DateTime clientConnectionTime) {
        DataValue value = DataValue.valueOnly(new Variant(clientConnectionTime));
        return getClientConnectionTimeNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value))
            .thenCompose(statusCode -> {
                if (statusCode != null && statusCode.isBad()) {
                    return FutureUtils.failedUaFuture(statusCode);
                } else {
                    return CompletableFuture.completedFuture(Unit.VALUE);
                }
            });
    }

    @Override
    public BaseDataVariableTypeNode getClientConnectionTimeNode() throws UaException {
        try {
            return getClientConnectionTimeNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getClientConnectionTimeNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "ClientConnectionTime", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=63"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public DateTime getClientLastContactTime() throws UaException {
        BaseDataVariableTypeNode node = getClientLastContactTimeNode();
        return (DateTime) node.getValue().getValue().getValue();
    }

    @Override
    public void setClientLastContactTime(DateTime clientLastContactTime) throws UaException {
        BaseDataVariableTypeNode node = getClientLastContactTimeNode();
        node.setValue(new Variant(clientLastContactTime));
    }

    @Override
    public DateTime readClientLastContactTime() throws UaException {
        try {
            return readClientLastContactTimeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeClientLastContactTime(DateTime clientLastContactTime) throws UaException {
        try {
            writeClientLastContactTimeAsync(clientLastContactTime).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends DateTime> readClientLastContactTimeAsync() {
        return getClientLastContactTimeNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (DateTime) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeClientLastContactTimeAsync(DateTime clientLastContactTime) {
        DataValue value = DataValue.valueOnly(new Variant(clientLastContactTime));
        return getClientLastContactTimeNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value))
            .thenCompose(statusCode -> {
                if (statusCode != null && statusCode.isBad()) {
                    return FutureUtils.failedUaFuture(statusCode);
                } else {
                    return CompletableFuture.completedFuture(Unit.VALUE);
                }
            });
    }

    @Override
    public BaseDataVariableTypeNode getClientLastContactTimeNode() throws UaException {
        try {
            return getClientLastContactTimeNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getClientLastContactTimeNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "ClientLastContactTime", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=63"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public UInteger getCurrentSubscriptionsCount() throws UaException {
        BaseDataVariableTypeNode node = getCurrentSubscriptionsCountNode();
        return (UInteger) node.getValue().getValue().getValue();
    }

    @Override
    public void setCurrentSubscriptionsCount(UInteger currentSubscriptionsCount) throws UaException {
        BaseDataVariableTypeNode node = getCurrentSubscriptionsCountNode();
        node.setValue(new Variant(currentSubscriptionsCount));
    }

    @Override
    public UInteger readCurrentSubscriptionsCount() throws UaException {
        try {
            return readCurrentSubscriptionsCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeCurrentSubscriptionsCount(UInteger currentSubscriptionsCount) throws
        UaException {
        try {
            writeCurrentSubscriptionsCountAsync(currentSubscriptionsCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UInteger> readCurrentSubscriptionsCountAsync() {
        return getCurrentSubscriptionsCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeCurrentSubscriptionsCountAsync(
        UInteger currentSubscriptionsCount) {
        DataValue value = DataValue.valueOnly(new Variant(currentSubscriptionsCount));
        return getCurrentSubscriptionsCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value))
            .thenCompose(statusCode -> {
                if (statusCode != null && statusCode.isBad()) {
                    return FutureUtils.failedUaFuture(statusCode);
                } else {
                    return CompletableFuture.completedFuture(Unit.VALUE);
                }
            });
    }

    @Override
    public BaseDataVariableTypeNode getCurrentSubscriptionsCountNode() throws UaException {
        try {
            return getCurrentSubscriptionsCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getCurrentSubscriptionsCountNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "CurrentSubscriptionsCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=63"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public UInteger getCurrentMonitoredItemsCount() throws UaException {
        BaseDataVariableTypeNode node = getCurrentMonitoredItemsCountNode();
        return (UInteger) node.getValue().getValue().getValue();
    }

    @Override
    public void setCurrentMonitoredItemsCount(UInteger currentMonitoredItemsCount) throws
        UaException {
        BaseDataVariableTypeNode node = getCurrentMonitoredItemsCountNode();
        node.setValue(new Variant(currentMonitoredItemsCount));
    }

    @Override
    public UInteger readCurrentMonitoredItemsCount() throws UaException {
        try {
            return readCurrentMonitoredItemsCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeCurrentMonitoredItemsCount(UInteger currentMonitoredItemsCount) throws
        UaException {
        try {
            writeCurrentMonitoredItemsCountAsync(currentMonitoredItemsCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UInteger> readCurrentMonitoredItemsCountAsync() {
        return getCurrentMonitoredItemsCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeCurrentMonitoredItemsCountAsync(
        UInteger currentMonitoredItemsCount) {
        DataValue value = DataValue.valueOnly(new Variant(currentMonitoredItemsCount));
        return getCurrentMonitoredItemsCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value))
            .thenCompose(statusCode -> {
                if (statusCode != null && statusCode.isBad()) {
                    return FutureUtils.failedUaFuture(statusCode);
                } else {
                    return CompletableFuture.completedFuture(Unit.VALUE);
                }
            });
    }

    @Override
    public BaseDataVariableTypeNode getCurrentMonitoredItemsCountNode() throws UaException {
        try {
            return getCurrentMonitoredItemsCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getCurrentMonitoredItemsCountNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "CurrentMonitoredItemsCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=63"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public UInteger getCurrentPublishRequestsInQueue() throws UaException {
        BaseDataVariableTypeNode node = getCurrentPublishRequestsInQueueNode();
        return (UInteger) node.getValue().getValue().getValue();
    }

    @Override
    public void setCurrentPublishRequestsInQueue(UInteger currentPublishRequestsInQueue) throws
        UaException {
        BaseDataVariableTypeNode node = getCurrentPublishRequestsInQueueNode();
        node.setValue(new Variant(currentPublishRequestsInQueue));
    }

    @Override
    public UInteger readCurrentPublishRequestsInQueue() throws UaException {
        try {
            return readCurrentPublishRequestsInQueueAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeCurrentPublishRequestsInQueue(UInteger currentPublishRequestsInQueue) throws
        UaException {
        try {
            writeCurrentPublishRequestsInQueueAsync(currentPublishRequestsInQueue).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UInteger> readCurrentPublishRequestsInQueueAsync() {
        return getCurrentPublishRequestsInQueueNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeCurrentPublishRequestsInQueueAsync(
        UInteger currentPublishRequestsInQueue) {
        DataValue value = DataValue.valueOnly(new Variant(currentPublishRequestsInQueue));
        return getCurrentPublishRequestsInQueueNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value))
            .thenCompose(statusCode -> {
                if (statusCode != null && statusCode.isBad()) {
                    return FutureUtils.failedUaFuture(statusCode);
                } else {
                    return CompletableFuture.completedFuture(Unit.VALUE);
                }
            });
    }

    @Override
    public BaseDataVariableTypeNode getCurrentPublishRequestsInQueueNode() throws UaException {
        try {
            return getCurrentPublishRequestsInQueueNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getCurrentPublishRequestsInQueueNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "CurrentPublishRequestsInQueue", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=63"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public ServiceCounterDataType getTotalRequestCount() throws UaException {
        BaseDataVariableTypeNode node = getTotalRequestCountNode();
        return (ServiceCounterDataType) node.getValue().getValue().getValue();
    }

    @Override
    public void setTotalRequestCount(ServiceCounterDataType totalRequestCount) throws UaException {
        BaseDataVariableTypeNode node = getTotalRequestCountNode();
        node.setValue(new Variant(totalRequestCount));
    }

    @Override
    public ServiceCounterDataType readTotalRequestCount() throws UaException {
        try {
            return readTotalRequestCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeTotalRequestCount(ServiceCounterDataType totalRequestCount) throws UaException {
        try {
            writeTotalRequestCountAsync(totalRequestCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ServiceCounterDataType> readTotalRequestCountAsync() {
        return getTotalRequestCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (ServiceCounterDataType) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeTotalRequestCountAsync(
        ServiceCounterDataType totalRequestCount) {
        DataValue value = DataValue.valueOnly(new Variant(totalRequestCount));
        return getTotalRequestCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value))
            .thenCompose(statusCode -> {
                if (statusCode != null && statusCode.isBad()) {
                    return FutureUtils.failedUaFuture(statusCode);
                } else {
                    return CompletableFuture.completedFuture(Unit.VALUE);
                }
            });
    }

    @Override
    public BaseDataVariableTypeNode getTotalRequestCountNode() throws UaException {
        try {
            return getTotalRequestCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getTotalRequestCountNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "TotalRequestCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=63"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public UInteger getUnauthorizedRequestCount() throws UaException {
        BaseDataVariableTypeNode node = getUnauthorizedRequestCountNode();
        return (UInteger) node.getValue().getValue().getValue();
    }

    @Override
    public void setUnauthorizedRequestCount(UInteger unauthorizedRequestCount) throws UaException {
        BaseDataVariableTypeNode node = getUnauthorizedRequestCountNode();
        node.setValue(new Variant(unauthorizedRequestCount));
    }

    @Override
    public UInteger readUnauthorizedRequestCount() throws UaException {
        try {
            return readUnauthorizedRequestCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeUnauthorizedRequestCount(UInteger unauthorizedRequestCount) throws UaException {
        try {
            writeUnauthorizedRequestCountAsync(unauthorizedRequestCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UInteger> readUnauthorizedRequestCountAsync() {
        return getUnauthorizedRequestCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeUnauthorizedRequestCountAsync(
        UInteger unauthorizedRequestCount) {
        DataValue value = DataValue.valueOnly(new Variant(unauthorizedRequestCount));
        return getUnauthorizedRequestCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value))
            .thenCompose(statusCode -> {
                if (statusCode != null && statusCode.isBad()) {
                    return FutureUtils.failedUaFuture(statusCode);
                } else {
                    return CompletableFuture.completedFuture(Unit.VALUE);
                }
            });
    }

    @Override
    public BaseDataVariableTypeNode getUnauthorizedRequestCountNode() throws UaException {
        try {
            return getUnauthorizedRequestCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getUnauthorizedRequestCountNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "UnauthorizedRequestCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=63"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public ServiceCounterDataType getReadCount() throws UaException {
        BaseDataVariableTypeNode node = getReadCountNode();
        return (ServiceCounterDataType) node.getValue().getValue().getValue();
    }

    @Override
    public void setReadCount(ServiceCounterDataType readCount) throws UaException {
        BaseDataVariableTypeNode node = getReadCountNode();
        node.setValue(new Variant(readCount));
    }

    @Override
    public ServiceCounterDataType readReadCount() throws UaException {
        try {
            return readReadCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeReadCount(ServiceCounterDataType readCount) throws UaException {
        try {
            writeReadCountAsync(readCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ServiceCounterDataType> readReadCountAsync() {
        return getReadCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (ServiceCounterDataType) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeReadCountAsync(ServiceCounterDataType readCount) {
        DataValue value = DataValue.valueOnly(new Variant(readCount));
        return getReadCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value))
            .thenCompose(statusCode -> {
                if (statusCode != null && statusCode.isBad()) {
                    return FutureUtils.failedUaFuture(statusCode);
                } else {
                    return CompletableFuture.completedFuture(Unit.VALUE);
                }
            });
    }

    @Override
    public BaseDataVariableTypeNode getReadCountNode() throws UaException {
        try {
            return getReadCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getReadCountNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "ReadCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=63"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public ServiceCounterDataType getHistoryReadCount() throws UaException {
        BaseDataVariableTypeNode node = getHistoryReadCountNode();
        return (ServiceCounterDataType) node.getValue().getValue().getValue();
    }

    @Override
    public void setHistoryReadCount(ServiceCounterDataType historyReadCount) throws UaException {
        BaseDataVariableTypeNode node = getHistoryReadCountNode();
        node.setValue(new Variant(historyReadCount));
    }

    @Override
    public ServiceCounterDataType readHistoryReadCount() throws UaException {
        try {
            return readHistoryReadCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeHistoryReadCount(ServiceCounterDataType historyReadCount) throws UaException {
        try {
            writeHistoryReadCountAsync(historyReadCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ServiceCounterDataType> readHistoryReadCountAsync() {
        return getHistoryReadCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (ServiceCounterDataType) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeHistoryReadCountAsync(
        ServiceCounterDataType historyReadCount) {
        DataValue value = DataValue.valueOnly(new Variant(historyReadCount));
        return getHistoryReadCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value))
            .thenCompose(statusCode -> {
                if (statusCode != null && statusCode.isBad()) {
                    return FutureUtils.failedUaFuture(statusCode);
                } else {
                    return CompletableFuture.completedFuture(Unit.VALUE);
                }
            });
    }

    @Override
    public BaseDataVariableTypeNode getHistoryReadCountNode() throws UaException {
        try {
            return getHistoryReadCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getHistoryReadCountNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "HistoryReadCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=63"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public ServiceCounterDataType getWriteCount() throws UaException {
        BaseDataVariableTypeNode node = getWriteCountNode();
        return (ServiceCounterDataType) node.getValue().getValue().getValue();
    }

    @Override
    public void setWriteCount(ServiceCounterDataType writeCount) throws UaException {
        BaseDataVariableTypeNode node = getWriteCountNode();
        node.setValue(new Variant(writeCount));
    }

    @Override
    public ServiceCounterDataType readWriteCount() throws UaException {
        try {
            return readWriteCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeWriteCount(ServiceCounterDataType writeCount) throws UaException {
        try {
            writeWriteCountAsync(writeCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ServiceCounterDataType> readWriteCountAsync() {
        return getWriteCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (ServiceCounterDataType) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeWriteCountAsync(ServiceCounterDataType writeCount) {
        DataValue value = DataValue.valueOnly(new Variant(writeCount));
        return getWriteCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value))
            .thenCompose(statusCode -> {
                if (statusCode != null && statusCode.isBad()) {
                    return FutureUtils.failedUaFuture(statusCode);
                } else {
                    return CompletableFuture.completedFuture(Unit.VALUE);
                }
            });
    }

    @Override
    public BaseDataVariableTypeNode getWriteCountNode() throws UaException {
        try {
            return getWriteCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getWriteCountNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "WriteCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=63"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public ServiceCounterDataType getHistoryUpdateCount() throws UaException {
        BaseDataVariableTypeNode node = getHistoryUpdateCountNode();
        return (ServiceCounterDataType) node.getValue().getValue().getValue();
    }

    @Override
    public void setHistoryUpdateCount(ServiceCounterDataType historyUpdateCount) throws UaException {
        BaseDataVariableTypeNode node = getHistoryUpdateCountNode();
        node.setValue(new Variant(historyUpdateCount));
    }

    @Override
    public ServiceCounterDataType readHistoryUpdateCount() throws UaException {
        try {
            return readHistoryUpdateCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeHistoryUpdateCount(ServiceCounterDataType historyUpdateCount) throws
        UaException {
        try {
            writeHistoryUpdateCountAsync(historyUpdateCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ServiceCounterDataType> readHistoryUpdateCountAsync() {
        return getHistoryUpdateCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (ServiceCounterDataType) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeHistoryUpdateCountAsync(
        ServiceCounterDataType historyUpdateCount) {
        DataValue value = DataValue.valueOnly(new Variant(historyUpdateCount));
        return getHistoryUpdateCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value))
            .thenCompose(statusCode -> {
                if (statusCode != null && statusCode.isBad()) {
                    return FutureUtils.failedUaFuture(statusCode);
                } else {
                    return CompletableFuture.completedFuture(Unit.VALUE);
                }
            });
    }

    @Override
    public BaseDataVariableTypeNode getHistoryUpdateCountNode() throws UaException {
        try {
            return getHistoryUpdateCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getHistoryUpdateCountNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "HistoryUpdateCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=63"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public ServiceCounterDataType getCallCount() throws UaException {
        BaseDataVariableTypeNode node = getCallCountNode();
        return (ServiceCounterDataType) node.getValue().getValue().getValue();
    }

    @Override
    public void setCallCount(ServiceCounterDataType callCount) throws UaException {
        BaseDataVariableTypeNode node = getCallCountNode();
        node.setValue(new Variant(callCount));
    }

    @Override
    public ServiceCounterDataType readCallCount() throws UaException {
        try {
            return readCallCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeCallCount(ServiceCounterDataType callCount) throws UaException {
        try {
            writeCallCountAsync(callCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ServiceCounterDataType> readCallCountAsync() {
        return getCallCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (ServiceCounterDataType) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeCallCountAsync(ServiceCounterDataType callCount) {
        DataValue value = DataValue.valueOnly(new Variant(callCount));
        return getCallCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value))
            .thenCompose(statusCode -> {
                if (statusCode != null && statusCode.isBad()) {
                    return FutureUtils.failedUaFuture(statusCode);
                } else {
                    return CompletableFuture.completedFuture(Unit.VALUE);
                }
            });
    }

    @Override
    public BaseDataVariableTypeNode getCallCountNode() throws UaException {
        try {
            return getCallCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getCallCountNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "CallCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=63"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public ServiceCounterDataType getCreateMonitoredItemsCount() throws UaException {
        BaseDataVariableTypeNode node = getCreateMonitoredItemsCountNode();
        return (ServiceCounterDataType) node.getValue().getValue().getValue();
    }

    @Override
    public void setCreateMonitoredItemsCount(ServiceCounterDataType createMonitoredItemsCount) throws
        UaException {
        BaseDataVariableTypeNode node = getCreateMonitoredItemsCountNode();
        node.setValue(new Variant(createMonitoredItemsCount));
    }

    @Override
    public ServiceCounterDataType readCreateMonitoredItemsCount() throws UaException {
        try {
            return readCreateMonitoredItemsCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeCreateMonitoredItemsCount(ServiceCounterDataType createMonitoredItemsCount)
        throws UaException {
        try {
            writeCreateMonitoredItemsCountAsync(createMonitoredItemsCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ServiceCounterDataType> readCreateMonitoredItemsCountAsync() {
        return getCreateMonitoredItemsCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (ServiceCounterDataType) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeCreateMonitoredItemsCountAsync(
        ServiceCounterDataType createMonitoredItemsCount) {
        DataValue value = DataValue.valueOnly(new Variant(createMonitoredItemsCount));
        return getCreateMonitoredItemsCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value))
            .thenCompose(statusCode -> {
                if (statusCode != null && statusCode.isBad()) {
                    return FutureUtils.failedUaFuture(statusCode);
                } else {
                    return CompletableFuture.completedFuture(Unit.VALUE);
                }
            });
    }

    @Override
    public BaseDataVariableTypeNode getCreateMonitoredItemsCountNode() throws UaException {
        try {
            return getCreateMonitoredItemsCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getCreateMonitoredItemsCountNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "CreateMonitoredItemsCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=63"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public ServiceCounterDataType getModifyMonitoredItemsCount() throws UaException {
        BaseDataVariableTypeNode node = getModifyMonitoredItemsCountNode();
        return (ServiceCounterDataType) node.getValue().getValue().getValue();
    }

    @Override
    public void setModifyMonitoredItemsCount(ServiceCounterDataType modifyMonitoredItemsCount) throws
        UaException {
        BaseDataVariableTypeNode node = getModifyMonitoredItemsCountNode();
        node.setValue(new Variant(modifyMonitoredItemsCount));
    }

    @Override
    public ServiceCounterDataType readModifyMonitoredItemsCount() throws UaException {
        try {
            return readModifyMonitoredItemsCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeModifyMonitoredItemsCount(ServiceCounterDataType modifyMonitoredItemsCount)
        throws UaException {
        try {
            writeModifyMonitoredItemsCountAsync(modifyMonitoredItemsCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ServiceCounterDataType> readModifyMonitoredItemsCountAsync() {
        return getModifyMonitoredItemsCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (ServiceCounterDataType) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeModifyMonitoredItemsCountAsync(
        ServiceCounterDataType modifyMonitoredItemsCount) {
        DataValue value = DataValue.valueOnly(new Variant(modifyMonitoredItemsCount));
        return getModifyMonitoredItemsCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value))
            .thenCompose(statusCode -> {
                if (statusCode != null && statusCode.isBad()) {
                    return FutureUtils.failedUaFuture(statusCode);
                } else {
                    return CompletableFuture.completedFuture(Unit.VALUE);
                }
            });
    }

    @Override
    public BaseDataVariableTypeNode getModifyMonitoredItemsCountNode() throws UaException {
        try {
            return getModifyMonitoredItemsCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getModifyMonitoredItemsCountNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "ModifyMonitoredItemsCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=63"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public ServiceCounterDataType getSetMonitoringModeCount() throws UaException {
        BaseDataVariableTypeNode node = getSetMonitoringModeCountNode();
        return (ServiceCounterDataType) node.getValue().getValue().getValue();
    }

    @Override
    public void setSetMonitoringModeCount(ServiceCounterDataType setMonitoringModeCount) throws
        UaException {
        BaseDataVariableTypeNode node = getSetMonitoringModeCountNode();
        node.setValue(new Variant(setMonitoringModeCount));
    }

    @Override
    public ServiceCounterDataType readSetMonitoringModeCount() throws UaException {
        try {
            return readSetMonitoringModeCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeSetMonitoringModeCount(ServiceCounterDataType setMonitoringModeCount) throws
        UaException {
        try {
            writeSetMonitoringModeCountAsync(setMonitoringModeCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ServiceCounterDataType> readSetMonitoringModeCountAsync() {
        return getSetMonitoringModeCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (ServiceCounterDataType) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeSetMonitoringModeCountAsync(
        ServiceCounterDataType setMonitoringModeCount) {
        DataValue value = DataValue.valueOnly(new Variant(setMonitoringModeCount));
        return getSetMonitoringModeCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value))
            .thenCompose(statusCode -> {
                if (statusCode != null && statusCode.isBad()) {
                    return FutureUtils.failedUaFuture(statusCode);
                } else {
                    return CompletableFuture.completedFuture(Unit.VALUE);
                }
            });
    }

    @Override
    public BaseDataVariableTypeNode getSetMonitoringModeCountNode() throws UaException {
        try {
            return getSetMonitoringModeCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getSetMonitoringModeCountNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "SetMonitoringModeCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=63"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public ServiceCounterDataType getSetTriggeringCount() throws UaException {
        BaseDataVariableTypeNode node = getSetTriggeringCountNode();
        return (ServiceCounterDataType) node.getValue().getValue().getValue();
    }

    @Override
    public void setSetTriggeringCount(ServiceCounterDataType setTriggeringCount) throws UaException {
        BaseDataVariableTypeNode node = getSetTriggeringCountNode();
        node.setValue(new Variant(setTriggeringCount));
    }

    @Override
    public ServiceCounterDataType readSetTriggeringCount() throws UaException {
        try {
            return readSetTriggeringCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeSetTriggeringCount(ServiceCounterDataType setTriggeringCount) throws
        UaException {
        try {
            writeSetTriggeringCountAsync(setTriggeringCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ServiceCounterDataType> readSetTriggeringCountAsync() {
        return getSetTriggeringCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (ServiceCounterDataType) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeSetTriggeringCountAsync(
        ServiceCounterDataType setTriggeringCount) {
        DataValue value = DataValue.valueOnly(new Variant(setTriggeringCount));
        return getSetTriggeringCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value))
            .thenCompose(statusCode -> {
                if (statusCode != null && statusCode.isBad()) {
                    return FutureUtils.failedUaFuture(statusCode);
                } else {
                    return CompletableFuture.completedFuture(Unit.VALUE);
                }
            });
    }

    @Override
    public BaseDataVariableTypeNode getSetTriggeringCountNode() throws UaException {
        try {
            return getSetTriggeringCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getSetTriggeringCountNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "SetTriggeringCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=63"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public ServiceCounterDataType getDeleteMonitoredItemsCount() throws UaException {
        BaseDataVariableTypeNode node = getDeleteMonitoredItemsCountNode();
        return (ServiceCounterDataType) node.getValue().getValue().getValue();
    }

    @Override
    public void setDeleteMonitoredItemsCount(ServiceCounterDataType deleteMonitoredItemsCount) throws
        UaException {
        BaseDataVariableTypeNode node = getDeleteMonitoredItemsCountNode();
        node.setValue(new Variant(deleteMonitoredItemsCount));
    }

    @Override
    public ServiceCounterDataType readDeleteMonitoredItemsCount() throws UaException {
        try {
            return readDeleteMonitoredItemsCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeDeleteMonitoredItemsCount(ServiceCounterDataType deleteMonitoredItemsCount)
        throws UaException {
        try {
            writeDeleteMonitoredItemsCountAsync(deleteMonitoredItemsCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ServiceCounterDataType> readDeleteMonitoredItemsCountAsync() {
        return getDeleteMonitoredItemsCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (ServiceCounterDataType) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeDeleteMonitoredItemsCountAsync(
        ServiceCounterDataType deleteMonitoredItemsCount) {
        DataValue value = DataValue.valueOnly(new Variant(deleteMonitoredItemsCount));
        return getDeleteMonitoredItemsCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value))
            .thenCompose(statusCode -> {
                if (statusCode != null && statusCode.isBad()) {
                    return FutureUtils.failedUaFuture(statusCode);
                } else {
                    return CompletableFuture.completedFuture(Unit.VALUE);
                }
            });
    }

    @Override
    public BaseDataVariableTypeNode getDeleteMonitoredItemsCountNode() throws UaException {
        try {
            return getDeleteMonitoredItemsCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getDeleteMonitoredItemsCountNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "DeleteMonitoredItemsCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=63"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public ServiceCounterDataType getCreateSubscriptionCount() throws UaException {
        BaseDataVariableTypeNode node = getCreateSubscriptionCountNode();
        return (ServiceCounterDataType) node.getValue().getValue().getValue();
    }

    @Override
    public void setCreateSubscriptionCount(ServiceCounterDataType createSubscriptionCount) throws
        UaException {
        BaseDataVariableTypeNode node = getCreateSubscriptionCountNode();
        node.setValue(new Variant(createSubscriptionCount));
    }

    @Override
    public ServiceCounterDataType readCreateSubscriptionCount() throws UaException {
        try {
            return readCreateSubscriptionCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeCreateSubscriptionCount(ServiceCounterDataType createSubscriptionCount) throws
        UaException {
        try {
            writeCreateSubscriptionCountAsync(createSubscriptionCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ServiceCounterDataType> readCreateSubscriptionCountAsync() {
        return getCreateSubscriptionCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (ServiceCounterDataType) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeCreateSubscriptionCountAsync(
        ServiceCounterDataType createSubscriptionCount) {
        DataValue value = DataValue.valueOnly(new Variant(createSubscriptionCount));
        return getCreateSubscriptionCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value))
            .thenCompose(statusCode -> {
                if (statusCode != null && statusCode.isBad()) {
                    return FutureUtils.failedUaFuture(statusCode);
                } else {
                    return CompletableFuture.completedFuture(Unit.VALUE);
                }
            });
    }

    @Override
    public BaseDataVariableTypeNode getCreateSubscriptionCountNode() throws UaException {
        try {
            return getCreateSubscriptionCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getCreateSubscriptionCountNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "CreateSubscriptionCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=63"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public ServiceCounterDataType getModifySubscriptionCount() throws UaException {
        BaseDataVariableTypeNode node = getModifySubscriptionCountNode();
        return (ServiceCounterDataType) node.getValue().getValue().getValue();
    }

    @Override
    public void setModifySubscriptionCount(ServiceCounterDataType modifySubscriptionCount) throws
        UaException {
        BaseDataVariableTypeNode node = getModifySubscriptionCountNode();
        node.setValue(new Variant(modifySubscriptionCount));
    }

    @Override
    public ServiceCounterDataType readModifySubscriptionCount() throws UaException {
        try {
            return readModifySubscriptionCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeModifySubscriptionCount(ServiceCounterDataType modifySubscriptionCount) throws
        UaException {
        try {
            writeModifySubscriptionCountAsync(modifySubscriptionCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ServiceCounterDataType> readModifySubscriptionCountAsync() {
        return getModifySubscriptionCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (ServiceCounterDataType) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeModifySubscriptionCountAsync(
        ServiceCounterDataType modifySubscriptionCount) {
        DataValue value = DataValue.valueOnly(new Variant(modifySubscriptionCount));
        return getModifySubscriptionCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value))
            .thenCompose(statusCode -> {
                if (statusCode != null && statusCode.isBad()) {
                    return FutureUtils.failedUaFuture(statusCode);
                } else {
                    return CompletableFuture.completedFuture(Unit.VALUE);
                }
            });
    }

    @Override
    public BaseDataVariableTypeNode getModifySubscriptionCountNode() throws UaException {
        try {
            return getModifySubscriptionCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getModifySubscriptionCountNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "ModifySubscriptionCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=63"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public ServiceCounterDataType getSetPublishingModeCount() throws UaException {
        BaseDataVariableTypeNode node = getSetPublishingModeCountNode();
        return (ServiceCounterDataType) node.getValue().getValue().getValue();
    }

    @Override
    public void setSetPublishingModeCount(ServiceCounterDataType setPublishingModeCount) throws
        UaException {
        BaseDataVariableTypeNode node = getSetPublishingModeCountNode();
        node.setValue(new Variant(setPublishingModeCount));
    }

    @Override
    public ServiceCounterDataType readSetPublishingModeCount() throws UaException {
        try {
            return readSetPublishingModeCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeSetPublishingModeCount(ServiceCounterDataType setPublishingModeCount) throws
        UaException {
        try {
            writeSetPublishingModeCountAsync(setPublishingModeCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ServiceCounterDataType> readSetPublishingModeCountAsync() {
        return getSetPublishingModeCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (ServiceCounterDataType) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeSetPublishingModeCountAsync(
        ServiceCounterDataType setPublishingModeCount) {
        DataValue value = DataValue.valueOnly(new Variant(setPublishingModeCount));
        return getSetPublishingModeCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value))
            .thenCompose(statusCode -> {
                if (statusCode != null && statusCode.isBad()) {
                    return FutureUtils.failedUaFuture(statusCode);
                } else {
                    return CompletableFuture.completedFuture(Unit.VALUE);
                }
            });
    }

    @Override
    public BaseDataVariableTypeNode getSetPublishingModeCountNode() throws UaException {
        try {
            return getSetPublishingModeCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getSetPublishingModeCountNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "SetPublishingModeCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=63"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public ServiceCounterDataType getPublishCount() throws UaException {
        BaseDataVariableTypeNode node = getPublishCountNode();
        return (ServiceCounterDataType) node.getValue().getValue().getValue();
    }

    @Override
    public void setPublishCount(ServiceCounterDataType publishCount) throws UaException {
        BaseDataVariableTypeNode node = getPublishCountNode();
        node.setValue(new Variant(publishCount));
    }

    @Override
    public ServiceCounterDataType readPublishCount() throws UaException {
        try {
            return readPublishCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writePublishCount(ServiceCounterDataType publishCount) throws UaException {
        try {
            writePublishCountAsync(publishCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ServiceCounterDataType> readPublishCountAsync() {
        return getPublishCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (ServiceCounterDataType) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writePublishCountAsync(ServiceCounterDataType publishCount) {
        DataValue value = DataValue.valueOnly(new Variant(publishCount));
        return getPublishCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value))
            .thenCompose(statusCode -> {
                if (statusCode != null && statusCode.isBad()) {
                    return FutureUtils.failedUaFuture(statusCode);
                } else {
                    return CompletableFuture.completedFuture(Unit.VALUE);
                }
            });
    }

    @Override
    public BaseDataVariableTypeNode getPublishCountNode() throws UaException {
        try {
            return getPublishCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getPublishCountNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "PublishCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=63"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public ServiceCounterDataType getRepublishCount() throws UaException {
        BaseDataVariableTypeNode node = getRepublishCountNode();
        return (ServiceCounterDataType) node.getValue().getValue().getValue();
    }

    @Override
    public void setRepublishCount(ServiceCounterDataType republishCount) throws UaException {
        BaseDataVariableTypeNode node = getRepublishCountNode();
        node.setValue(new Variant(republishCount));
    }

    @Override
    public ServiceCounterDataType readRepublishCount() throws UaException {
        try {
            return readRepublishCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeRepublishCount(ServiceCounterDataType republishCount) throws UaException {
        try {
            writeRepublishCountAsync(republishCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ServiceCounterDataType> readRepublishCountAsync() {
        return getRepublishCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (ServiceCounterDataType) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeRepublishCountAsync(ServiceCounterDataType republishCount) {
        DataValue value = DataValue.valueOnly(new Variant(republishCount));
        return getRepublishCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value))
            .thenCompose(statusCode -> {
                if (statusCode != null && statusCode.isBad()) {
                    return FutureUtils.failedUaFuture(statusCode);
                } else {
                    return CompletableFuture.completedFuture(Unit.VALUE);
                }
            });
    }

    @Override
    public BaseDataVariableTypeNode getRepublishCountNode() throws UaException {
        try {
            return getRepublishCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getRepublishCountNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "RepublishCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=63"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public ServiceCounterDataType getTransferSubscriptionsCount() throws UaException {
        BaseDataVariableTypeNode node = getTransferSubscriptionsCountNode();
        return (ServiceCounterDataType) node.getValue().getValue().getValue();
    }

    @Override
    public void setTransferSubscriptionsCount(ServiceCounterDataType transferSubscriptionsCount)
        throws UaException {
        BaseDataVariableTypeNode node = getTransferSubscriptionsCountNode();
        node.setValue(new Variant(transferSubscriptionsCount));
    }

    @Override
    public ServiceCounterDataType readTransferSubscriptionsCount() throws UaException {
        try {
            return readTransferSubscriptionsCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeTransferSubscriptionsCount(ServiceCounterDataType transferSubscriptionsCount)
        throws UaException {
        try {
            writeTransferSubscriptionsCountAsync(transferSubscriptionsCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ServiceCounterDataType> readTransferSubscriptionsCountAsync() {
        return getTransferSubscriptionsCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (ServiceCounterDataType) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeTransferSubscriptionsCountAsync(
        ServiceCounterDataType transferSubscriptionsCount) {
        DataValue value = DataValue.valueOnly(new Variant(transferSubscriptionsCount));
        return getTransferSubscriptionsCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value))
            .thenCompose(statusCode -> {
                if (statusCode != null && statusCode.isBad()) {
                    return FutureUtils.failedUaFuture(statusCode);
                } else {
                    return CompletableFuture.completedFuture(Unit.VALUE);
                }
            });
    }

    @Override
    public BaseDataVariableTypeNode getTransferSubscriptionsCountNode() throws UaException {
        try {
            return getTransferSubscriptionsCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getTransferSubscriptionsCountNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "TransferSubscriptionsCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=63"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public ServiceCounterDataType getDeleteSubscriptionsCount() throws UaException {
        BaseDataVariableTypeNode node = getDeleteSubscriptionsCountNode();
        return (ServiceCounterDataType) node.getValue().getValue().getValue();
    }

    @Override
    public void setDeleteSubscriptionsCount(ServiceCounterDataType deleteSubscriptionsCount) throws
        UaException {
        BaseDataVariableTypeNode node = getDeleteSubscriptionsCountNode();
        node.setValue(new Variant(deleteSubscriptionsCount));
    }

    @Override
    public ServiceCounterDataType readDeleteSubscriptionsCount() throws UaException {
        try {
            return readDeleteSubscriptionsCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeDeleteSubscriptionsCount(ServiceCounterDataType deleteSubscriptionsCount) throws
        UaException {
        try {
            writeDeleteSubscriptionsCountAsync(deleteSubscriptionsCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ServiceCounterDataType> readDeleteSubscriptionsCountAsync() {
        return getDeleteSubscriptionsCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (ServiceCounterDataType) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeDeleteSubscriptionsCountAsync(
        ServiceCounterDataType deleteSubscriptionsCount) {
        DataValue value = DataValue.valueOnly(new Variant(deleteSubscriptionsCount));
        return getDeleteSubscriptionsCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value))
            .thenCompose(statusCode -> {
                if (statusCode != null && statusCode.isBad()) {
                    return FutureUtils.failedUaFuture(statusCode);
                } else {
                    return CompletableFuture.completedFuture(Unit.VALUE);
                }
            });
    }

    @Override
    public BaseDataVariableTypeNode getDeleteSubscriptionsCountNode() throws UaException {
        try {
            return getDeleteSubscriptionsCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getDeleteSubscriptionsCountNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "DeleteSubscriptionsCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=63"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public ServiceCounterDataType getAddNodesCount() throws UaException {
        BaseDataVariableTypeNode node = getAddNodesCountNode();
        return (ServiceCounterDataType) node.getValue().getValue().getValue();
    }

    @Override
    public void setAddNodesCount(ServiceCounterDataType addNodesCount) throws UaException {
        BaseDataVariableTypeNode node = getAddNodesCountNode();
        node.setValue(new Variant(addNodesCount));
    }

    @Override
    public ServiceCounterDataType readAddNodesCount() throws UaException {
        try {
            return readAddNodesCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeAddNodesCount(ServiceCounterDataType addNodesCount) throws UaException {
        try {
            writeAddNodesCountAsync(addNodesCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ServiceCounterDataType> readAddNodesCountAsync() {
        return getAddNodesCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (ServiceCounterDataType) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeAddNodesCountAsync(ServiceCounterDataType addNodesCount) {
        DataValue value = DataValue.valueOnly(new Variant(addNodesCount));
        return getAddNodesCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value))
            .thenCompose(statusCode -> {
                if (statusCode != null && statusCode.isBad()) {
                    return FutureUtils.failedUaFuture(statusCode);
                } else {
                    return CompletableFuture.completedFuture(Unit.VALUE);
                }
            });
    }

    @Override
    public BaseDataVariableTypeNode getAddNodesCountNode() throws UaException {
        try {
            return getAddNodesCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getAddNodesCountNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "AddNodesCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=63"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public ServiceCounterDataType getAddReferencesCount() throws UaException {
        BaseDataVariableTypeNode node = getAddReferencesCountNode();
        return (ServiceCounterDataType) node.getValue().getValue().getValue();
    }

    @Override
    public void setAddReferencesCount(ServiceCounterDataType addReferencesCount) throws UaException {
        BaseDataVariableTypeNode node = getAddReferencesCountNode();
        node.setValue(new Variant(addReferencesCount));
    }

    @Override
    public ServiceCounterDataType readAddReferencesCount() throws UaException {
        try {
            return readAddReferencesCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeAddReferencesCount(ServiceCounterDataType addReferencesCount) throws
        UaException {
        try {
            writeAddReferencesCountAsync(addReferencesCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ServiceCounterDataType> readAddReferencesCountAsync() {
        return getAddReferencesCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (ServiceCounterDataType) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeAddReferencesCountAsync(
        ServiceCounterDataType addReferencesCount) {
        DataValue value = DataValue.valueOnly(new Variant(addReferencesCount));
        return getAddReferencesCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value))
            .thenCompose(statusCode -> {
                if (statusCode != null && statusCode.isBad()) {
                    return FutureUtils.failedUaFuture(statusCode);
                } else {
                    return CompletableFuture.completedFuture(Unit.VALUE);
                }
            });
    }

    @Override
    public BaseDataVariableTypeNode getAddReferencesCountNode() throws UaException {
        try {
            return getAddReferencesCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getAddReferencesCountNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "AddReferencesCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=63"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public ServiceCounterDataType getDeleteNodesCount() throws UaException {
        BaseDataVariableTypeNode node = getDeleteNodesCountNode();
        return (ServiceCounterDataType) node.getValue().getValue().getValue();
    }

    @Override
    public void setDeleteNodesCount(ServiceCounterDataType deleteNodesCount) throws UaException {
        BaseDataVariableTypeNode node = getDeleteNodesCountNode();
        node.setValue(new Variant(deleteNodesCount));
    }

    @Override
    public ServiceCounterDataType readDeleteNodesCount() throws UaException {
        try {
            return readDeleteNodesCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeDeleteNodesCount(ServiceCounterDataType deleteNodesCount) throws UaException {
        try {
            writeDeleteNodesCountAsync(deleteNodesCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ServiceCounterDataType> readDeleteNodesCountAsync() {
        return getDeleteNodesCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (ServiceCounterDataType) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeDeleteNodesCountAsync(
        ServiceCounterDataType deleteNodesCount) {
        DataValue value = DataValue.valueOnly(new Variant(deleteNodesCount));
        return getDeleteNodesCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value))
            .thenCompose(statusCode -> {
                if (statusCode != null && statusCode.isBad()) {
                    return FutureUtils.failedUaFuture(statusCode);
                } else {
                    return CompletableFuture.completedFuture(Unit.VALUE);
                }
            });
    }

    @Override
    public BaseDataVariableTypeNode getDeleteNodesCountNode() throws UaException {
        try {
            return getDeleteNodesCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getDeleteNodesCountNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "DeleteNodesCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=63"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public ServiceCounterDataType getDeleteReferencesCount() throws UaException {
        BaseDataVariableTypeNode node = getDeleteReferencesCountNode();
        return (ServiceCounterDataType) node.getValue().getValue().getValue();
    }

    @Override
    public void setDeleteReferencesCount(ServiceCounterDataType deleteReferencesCount) throws
        UaException {
        BaseDataVariableTypeNode node = getDeleteReferencesCountNode();
        node.setValue(new Variant(deleteReferencesCount));
    }

    @Override
    public ServiceCounterDataType readDeleteReferencesCount() throws UaException {
        try {
            return readDeleteReferencesCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeDeleteReferencesCount(ServiceCounterDataType deleteReferencesCount) throws
        UaException {
        try {
            writeDeleteReferencesCountAsync(deleteReferencesCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ServiceCounterDataType> readDeleteReferencesCountAsync() {
        return getDeleteReferencesCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (ServiceCounterDataType) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeDeleteReferencesCountAsync(
        ServiceCounterDataType deleteReferencesCount) {
        DataValue value = DataValue.valueOnly(new Variant(deleteReferencesCount));
        return getDeleteReferencesCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value))
            .thenCompose(statusCode -> {
                if (statusCode != null && statusCode.isBad()) {
                    return FutureUtils.failedUaFuture(statusCode);
                } else {
                    return CompletableFuture.completedFuture(Unit.VALUE);
                }
            });
    }

    @Override
    public BaseDataVariableTypeNode getDeleteReferencesCountNode() throws UaException {
        try {
            return getDeleteReferencesCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getDeleteReferencesCountNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "DeleteReferencesCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=63"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public ServiceCounterDataType getBrowseCount() throws UaException {
        BaseDataVariableTypeNode node = getBrowseCountNode();
        return (ServiceCounterDataType) node.getValue().getValue().getValue();
    }

    @Override
    public void setBrowseCount(ServiceCounterDataType browseCount) throws UaException {
        BaseDataVariableTypeNode node = getBrowseCountNode();
        node.setValue(new Variant(browseCount));
    }

    @Override
    public ServiceCounterDataType readBrowseCount() throws UaException {
        try {
            return readBrowseCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeBrowseCount(ServiceCounterDataType browseCount) throws UaException {
        try {
            writeBrowseCountAsync(browseCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ServiceCounterDataType> readBrowseCountAsync() {
        return getBrowseCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (ServiceCounterDataType) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeBrowseCountAsync(ServiceCounterDataType browseCount) {
        DataValue value = DataValue.valueOnly(new Variant(browseCount));
        return getBrowseCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value))
            .thenCompose(statusCode -> {
                if (statusCode != null && statusCode.isBad()) {
                    return FutureUtils.failedUaFuture(statusCode);
                } else {
                    return CompletableFuture.completedFuture(Unit.VALUE);
                }
            });
    }

    @Override
    public BaseDataVariableTypeNode getBrowseCountNode() throws UaException {
        try {
            return getBrowseCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getBrowseCountNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "BrowseCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=63"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public ServiceCounterDataType getBrowseNextCount() throws UaException {
        BaseDataVariableTypeNode node = getBrowseNextCountNode();
        return (ServiceCounterDataType) node.getValue().getValue().getValue();
    }

    @Override
    public void setBrowseNextCount(ServiceCounterDataType browseNextCount) throws UaException {
        BaseDataVariableTypeNode node = getBrowseNextCountNode();
        node.setValue(new Variant(browseNextCount));
    }

    @Override
    public ServiceCounterDataType readBrowseNextCount() throws UaException {
        try {
            return readBrowseNextCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeBrowseNextCount(ServiceCounterDataType browseNextCount) throws UaException {
        try {
            writeBrowseNextCountAsync(browseNextCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ServiceCounterDataType> readBrowseNextCountAsync() {
        return getBrowseNextCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (ServiceCounterDataType) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeBrowseNextCountAsync(ServiceCounterDataType browseNextCount) {
        DataValue value = DataValue.valueOnly(new Variant(browseNextCount));
        return getBrowseNextCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value))
            .thenCompose(statusCode -> {
                if (statusCode != null && statusCode.isBad()) {
                    return FutureUtils.failedUaFuture(statusCode);
                } else {
                    return CompletableFuture.completedFuture(Unit.VALUE);
                }
            });
    }

    @Override
    public BaseDataVariableTypeNode getBrowseNextCountNode() throws UaException {
        try {
            return getBrowseNextCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getBrowseNextCountNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "BrowseNextCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=63"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public ServiceCounterDataType getTranslateBrowsePathsToNodeIdsCount() throws UaException {
        BaseDataVariableTypeNode node = getTranslateBrowsePathsToNodeIdsCountNode();
        return (ServiceCounterDataType) node.getValue().getValue().getValue();
    }

    @Override
    public void setTranslateBrowsePathsToNodeIdsCount(
        ServiceCounterDataType translateBrowsePathsToNodeIdsCount) throws UaException {
        BaseDataVariableTypeNode node = getTranslateBrowsePathsToNodeIdsCountNode();
        node.setValue(new Variant(translateBrowsePathsToNodeIdsCount));
    }

    @Override
    public ServiceCounterDataType readTranslateBrowsePathsToNodeIdsCount() throws UaException {
        try {
            return readTranslateBrowsePathsToNodeIdsCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeTranslateBrowsePathsToNodeIdsCount(
        ServiceCounterDataType translateBrowsePathsToNodeIdsCount) throws UaException {
        try {
            writeTranslateBrowsePathsToNodeIdsCountAsync(translateBrowsePathsToNodeIdsCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ServiceCounterDataType> readTranslateBrowsePathsToNodeIdsCountAsync(
    ) {
        return getTranslateBrowsePathsToNodeIdsCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (ServiceCounterDataType) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeTranslateBrowsePathsToNodeIdsCountAsync(
        ServiceCounterDataType translateBrowsePathsToNodeIdsCount) {
        DataValue value = DataValue.valueOnly(new Variant(translateBrowsePathsToNodeIdsCount));
        return getTranslateBrowsePathsToNodeIdsCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value))
            .thenCompose(statusCode -> {
                if (statusCode != null && statusCode.isBad()) {
                    return FutureUtils.failedUaFuture(statusCode);
                } else {
                    return CompletableFuture.completedFuture(Unit.VALUE);
                }
            });
    }

    @Override
    public BaseDataVariableTypeNode getTranslateBrowsePathsToNodeIdsCountNode() throws UaException {
        try {
            return getTranslateBrowsePathsToNodeIdsCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getTranslateBrowsePathsToNodeIdsCountNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "TranslateBrowsePathsToNodeIdsCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=63"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public ServiceCounterDataType getQueryFirstCount() throws UaException {
        BaseDataVariableTypeNode node = getQueryFirstCountNode();
        return (ServiceCounterDataType) node.getValue().getValue().getValue();
    }

    @Override
    public void setQueryFirstCount(ServiceCounterDataType queryFirstCount) throws UaException {
        BaseDataVariableTypeNode node = getQueryFirstCountNode();
        node.setValue(new Variant(queryFirstCount));
    }

    @Override
    public ServiceCounterDataType readQueryFirstCount() throws UaException {
        try {
            return readQueryFirstCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeQueryFirstCount(ServiceCounterDataType queryFirstCount) throws UaException {
        try {
            writeQueryFirstCountAsync(queryFirstCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ServiceCounterDataType> readQueryFirstCountAsync() {
        return getQueryFirstCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (ServiceCounterDataType) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeQueryFirstCountAsync(ServiceCounterDataType queryFirstCount) {
        DataValue value = DataValue.valueOnly(new Variant(queryFirstCount));
        return getQueryFirstCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value))
            .thenCompose(statusCode -> {
                if (statusCode != null && statusCode.isBad()) {
                    return FutureUtils.failedUaFuture(statusCode);
                } else {
                    return CompletableFuture.completedFuture(Unit.VALUE);
                }
            });
    }

    @Override
    public BaseDataVariableTypeNode getQueryFirstCountNode() throws UaException {
        try {
            return getQueryFirstCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getQueryFirstCountNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "QueryFirstCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=63"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public ServiceCounterDataType getQueryNextCount() throws UaException {
        BaseDataVariableTypeNode node = getQueryNextCountNode();
        return (ServiceCounterDataType) node.getValue().getValue().getValue();
    }

    @Override
    public void setQueryNextCount(ServiceCounterDataType queryNextCount) throws UaException {
        BaseDataVariableTypeNode node = getQueryNextCountNode();
        node.setValue(new Variant(queryNextCount));
    }

    @Override
    public ServiceCounterDataType readQueryNextCount() throws UaException {
        try {
            return readQueryNextCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeQueryNextCount(ServiceCounterDataType queryNextCount) throws UaException {
        try {
            writeQueryNextCountAsync(queryNextCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ServiceCounterDataType> readQueryNextCountAsync() {
        return getQueryNextCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (ServiceCounterDataType) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeQueryNextCountAsync(ServiceCounterDataType queryNextCount) {
        DataValue value = DataValue.valueOnly(new Variant(queryNextCount));
        return getQueryNextCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value))
            .thenCompose(statusCode -> {
                if (statusCode != null && statusCode.isBad()) {
                    return FutureUtils.failedUaFuture(statusCode);
                } else {
                    return CompletableFuture.completedFuture(Unit.VALUE);
                }
            });
    }

    @Override
    public BaseDataVariableTypeNode getQueryNextCountNode() throws UaException {
        try {
            return getQueryNextCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getQueryNextCountNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "QueryNextCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=63"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public ServiceCounterDataType getRegisterNodesCount() throws UaException {
        BaseDataVariableTypeNode node = getRegisterNodesCountNode();
        return (ServiceCounterDataType) node.getValue().getValue().getValue();
    }

    @Override
    public void setRegisterNodesCount(ServiceCounterDataType registerNodesCount) throws UaException {
        BaseDataVariableTypeNode node = getRegisterNodesCountNode();
        node.setValue(new Variant(registerNodesCount));
    }

    @Override
    public ServiceCounterDataType readRegisterNodesCount() throws UaException {
        try {
            return readRegisterNodesCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeRegisterNodesCount(ServiceCounterDataType registerNodesCount) throws
        UaException {
        try {
            writeRegisterNodesCountAsync(registerNodesCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ServiceCounterDataType> readRegisterNodesCountAsync() {
        return getRegisterNodesCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (ServiceCounterDataType) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeRegisterNodesCountAsync(
        ServiceCounterDataType registerNodesCount) {
        DataValue value = DataValue.valueOnly(new Variant(registerNodesCount));
        return getRegisterNodesCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value))
            .thenCompose(statusCode -> {
                if (statusCode != null && statusCode.isBad()) {
                    return FutureUtils.failedUaFuture(statusCode);
                } else {
                    return CompletableFuture.completedFuture(Unit.VALUE);
                }
            });
    }

    @Override
    public BaseDataVariableTypeNode getRegisterNodesCountNode() throws UaException {
        try {
            return getRegisterNodesCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getRegisterNodesCountNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "RegisterNodesCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=63"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public ServiceCounterDataType getUnregisterNodesCount() throws UaException {
        BaseDataVariableTypeNode node = getUnregisterNodesCountNode();
        return (ServiceCounterDataType) node.getValue().getValue().getValue();
    }

    @Override
    public void setUnregisterNodesCount(ServiceCounterDataType unregisterNodesCount) throws
        UaException {
        BaseDataVariableTypeNode node = getUnregisterNodesCountNode();
        node.setValue(new Variant(unregisterNodesCount));
    }

    @Override
    public ServiceCounterDataType readUnregisterNodesCount() throws UaException {
        try {
            return readUnregisterNodesCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeUnregisterNodesCount(ServiceCounterDataType unregisterNodesCount) throws
        UaException {
        try {
            writeUnregisterNodesCountAsync(unregisterNodesCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ServiceCounterDataType> readUnregisterNodesCountAsync() {
        return getUnregisterNodesCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (ServiceCounterDataType) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeUnregisterNodesCountAsync(
        ServiceCounterDataType unregisterNodesCount) {
        DataValue value = DataValue.valueOnly(new Variant(unregisterNodesCount));
        return getUnregisterNodesCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value))
            .thenCompose(statusCode -> {
                if (statusCode != null && statusCode.isBad()) {
                    return FutureUtils.failedUaFuture(statusCode);
                } else {
                    return CompletableFuture.completedFuture(Unit.VALUE);
                }
            });
    }

    @Override
    public BaseDataVariableTypeNode getUnregisterNodesCountNode() throws UaException {
        try {
            return getUnregisterNodesCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getUnregisterNodesCountNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "UnregisterNodesCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=63"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }
}
